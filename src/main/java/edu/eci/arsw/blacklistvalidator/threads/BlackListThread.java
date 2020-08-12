package edu.eci.arsw.blacklistvalidator.threads;

import edu.eci.arsw.blacklistvalidator.HostBlackListsValidator;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.List;

public class BlackListThread extends Thread {
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private int ocurrencesCount, initialServ, finalServ;
    private String ipAddress;
    private List<Integer> blackListOcurrences;
    private HostBlacklistsDataSourceFacade facade;

    public BlackListThread(String ipAddress, HostBlacklistsDataSourceFacade facade, int initalServ, int finalServ){
        this.ipAddress = ipAddress;
        this.facade = facade;
        this.initialServ = initalServ;
        this.finalServ = finalServ;
    }

    public void run(){

        for (int i=initialServ;i<finalServ && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){

            if (facade.isInBlackListServer(i, ipAddress)){

                blackListOcurrences.add(i);

                ocurrencesCount++;
            }
        }
    }

    public int getOcurrencesCount() {
        return ocurrencesCount;
    }

    public void setOcurrencesCount(int ocurrencesCount) {
        this.ocurrencesCount = ocurrencesCount;
    }

    public int getInitialServ() {
        return initialServ;
    }

    public void setInitialServ(int initialServ) {
        this.initialServ = initialServ;
    }

    public int getFinalServ() {
        return finalServ;
    }

    public void setFinalServ(int finalServ) {
        this.finalServ = finalServ;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    public void setBlackListOcurrences(List<Integer> blackListOcurrences) {
        this.blackListOcurrences = blackListOcurrences;
    }

    public HostBlacklistsDataSourceFacade getFacade() {
        return facade;
    }

    public void setFacade(HostBlacklistsDataSourceFacade facade) {
        this.facade = facade;
    }
}
