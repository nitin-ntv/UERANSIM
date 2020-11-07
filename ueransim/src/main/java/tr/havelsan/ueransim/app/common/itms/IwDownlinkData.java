package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class IwDownlinkData {
    public final UUID ueId;
    public final OctetString ipPacket;

    public IwDownlinkData(UUID ueId, OctetString ipPacket) {
        this.ueId = ueId;
        this.ipPacket = ipPacket;
    }
}
