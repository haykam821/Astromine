package com.github.chainmailstudios.astromine.common.volume.collection;

import com.github.chainmailstudios.astromine.common.volume.BaseVolume;

public interface AgnosticIndexedVolumeCollection {
	boolean contains(int volumeType);

	<T extends BaseVolume> T get(int volumeType);
}