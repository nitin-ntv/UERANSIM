/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.events.GnbEvent;
import tr.havelsan.ueransim.sctp.ISCTPClient;
import tr.havelsan.ueransim.structs.GnbConfig;

import java.util.ArrayDeque;
import java.util.Queue;

public class GnbSimContext {
    public final SimulationContext simCtx;

    public GnbConfig config;

    public ISCTPClient sctpClient;
    public int streamNumber;

    public Long amfUeNgapId;
    public long ranUeNgapId;

    Queue<GnbEvent> commandQueue = new ArrayDeque<>();

    public GnbSimContext(SimulationContext simCtx) {
        this.simCtx = simCtx;
    }
}
