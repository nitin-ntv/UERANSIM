package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class RegistrationRequest extends PlainNasMessage {
    public IE5gsRegistrationType registrationType;
    public IENasKeySetIdentifier nasKeySetIdentifier;
    public IE5gsMobileIdentity mobileIdentity;

    /* Optional fields */
    public IEUeSecurityCapability ueSecurityCapability;
    public IE5gMmCapability mmCapability;
    public IENssai requestedNSSA;
    public IEMicoIndication micoIndication;
    public IENetworkSlicingIndication networkSlicingIndication;
    public IENasKeySetIdentifier nonCurrentNgKsi;
    public IE5gsMobileIdentity additionalGuti;
    public IE5gsDrxParameters requestedDrxParameters;
    public IEUesUsageSetting uesUsageSetting;
    public IE5gsUpdateType updateType;

    @Override
    public RegistrationRequest decodeMessage(OctetInputStream stream) {
        var req = new RegistrationRequest();

        int octet = stream.readOctetI();
        req.registrationType = NasDecoder.ie1(octet & 0xF, IE5gsRegistrationType.class);
        req.nasKeySetIdentifier = NasDecoder.ie1(octet >> 4, IENasKeySetIdentifier.class);
        req.mobileIdentity = NasDecoder.mobileIdentity(stream, false);

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            int msb = iei >> 4 & 0xF;
            if (msb == 0xC || msb == 0xB || msb == 0x9) {
                int lsb = iei & 0xF;
                switch (msb) {
                    case 0xC:
                        this.nonCurrentNgKsi = NasDecoder.ie1(lsb, IENasKeySetIdentifier.class);
                        break;
                    case 0xB:
                        this.micoIndication = NasDecoder.ie1(lsb, IEMicoIndication.class);
                        break;
                    case 0x9:
                        this.networkSlicingIndication = NasDecoder.ie1(lsb, IENetworkSlicingIndication.class);
                        break;
                }
            } else {
                switch (iei) {
                    case 0x10:
                        req.mmCapability = NasDecoder.ie2346(stream, false, IE5gMmCapability.class);
                        break;
                    case 0x2E:
                        req.ueSecurityCapability = NasDecoder.ie2346(stream, false, IEUeSecurityCapability.class);
                        break;
                    case 0x2F:
                        req.requestedNSSA = NasDecoder.ie2346(stream, false, IENssai.class);
                        break;
                    case 0x52:
                        throw new NotImplementedException("not implemented yet: Last visited registered TAI");
                    case 0x17:
                        throw new NotImplementedException("not implemented yet: S1 UE network capability");
                    case 0x40:
                        throw new NotImplementedException("not implemented yet: Uplink data status");
                    case 0x50:
                        throw new NotImplementedException("not implemented yet: PDU session status");
                    case 0x2B:
                        throw new NotImplementedException("not implemented yet: UE status");
                    case 0x77:
                        this.additionalGuti = NasDecoder.ie2346(stream, false, IE5gsMobileIdentity.class);
                        break;
                    case 0x25:
                        throw new NotImplementedException("not implemented yet: Allowed PDU session status");
                    case 0x18:
                        this.uesUsageSetting = NasDecoder.ie2346(stream, false, IEUesUsageSetting.class);
                        break;
                    case 0x51:
                        this.requestedDrxParameters = NasDecoder.ie2346(stream, false, IE5gsDrxParameters.class);
                        break;
                    case 0x70:
                        throw new NotImplementedException("not implemented yet: EPS NAS message container");
                    case 0x7E:
                        throw new NotImplementedException("not implemented yet: LADN indication");
                    case 0x7B:
                        throw new NotImplementedException("not implemented yet: Payload container");
                    case 0x53:
                        this.updateType = NasDecoder.ie2346(stream, false, IE5gsUpdateType.class);
                        break;
                    case 0x71:
                        throw new NotImplementedException("not implemented yet: NAS message container");
                    default:
                        throw new InvalidValueException("iei is invalid: " + iei);
                }
            }
        }

        return req;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        NasEncoder.ie1(stream, nasKeySetIdentifier, registrationType);
        NasEncoder.ie2346(stream, mobileIdentity);

        if (ueSecurityCapability != null) {
            NasEncoder.ie2346(stream, 0x2E, ueSecurityCapability);
        }
    }
}
