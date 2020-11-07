package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class IwUplinkData {
    public final UUID ueId;
    public final int pduSessionId;
    public final OctetString ipData;

    public IwUplinkData(UUID ueId, int pduSessionId, OctetString ipData) {
        this.ueId = ueId;
        this.pduSessionId = pduSessionId;
        this.ipData = ipData;
    }
}
