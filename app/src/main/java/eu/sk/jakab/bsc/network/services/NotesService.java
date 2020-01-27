package eu.sk.jakab.bsc.network.services;

import eu.sk.jakab.bsc.network.BaseApiClient;
import eu.sk.jakab.bsc.network.api.NotesAPI;

public class NotesService {
    private static final Object sInstanceLock = new Object();
    private static NotesAPI sINSTANCE = null;

    public static NotesAPI getInstance() {
        if (sINSTANCE == null) {
            synchronized (sInstanceLock) {
                if (sINSTANCE == null)
                    sINSTANCE = BaseApiClient.getInstance().createService(NotesAPI.class);
            }
        }

        return sINSTANCE;
    }
}
