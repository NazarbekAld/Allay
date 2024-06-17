package org.allaymc.api.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.datastruct.SemVersion;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671;
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685;

@UtilityClass
public final class ProtocolInfo {

    public static final BedrockCodec PACKET_CODEC = Bedrock_v685.CODEC;

    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 21, 0, 3, 0);

    public static final int BLOCK_STATE_VERSION_NO_REVISION = (MINECRAFT_VERSION.major() << 24) | // major
            (MINECRAFT_VERSION.minor() << 16) | // minor
            (MINECRAFT_VERSION.patch() << 8); // patch

    public static final int BLOCK_STATE_VERSION = BLOCK_STATE_VERSION_NO_REVISION | MINECRAFT_VERSION.revision(); // revision
}
