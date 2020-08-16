package edu.eci.arsw.blacklistvalidator.threads;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BlackListThread extends Thread {
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private AtomicInteger ocurrencesCount,checkListsCount;
    private int initialServ,finalServ;
    private String ipAddress;
    private List<Integer> blackListOcurrences;
    private HostBlacklistsDataSourceFacade facade;


    public BlackListThread(String ipAddress, HostBlacklistsDataSourceFacade facade,List<Integer> blackListOcurrences,AtomicInteger checkListsCount,AtomicInteger ocurrencesCount, int initalServ, int finalServ){
        this.ipAddress = ipAddress;
        this.facade = facade;
        this.initialServ = initalServ;
        this.finalServ = finalServ;
        this.blackListOcurrences = blackListOcurrences;
        this.ocurrencesCount = ocurrencesCount;
        this.checkListsCount = checkListsCount;
    }

    public void run(){

        for (int i=initialServ;i<finalServ && ocurrencesCount.get()<BLACK_LIST_ALARM_COUNT;i++){

            checkListsCount.getAndIncrement();
            if (facade.isInBlackListServer(i, ipAddress)){
                blackListOcurrences.add(i);
                ocurrencesCount.getAndIncrement();
            }
        }


    }

    public AtomicInteger getOcurrencesCount() {
        return ocurrencesCount;
    }

    public void setOcurrencesCount(AtomicInteger ocurrencesCount) {
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

    public AtomicInteger getCheckListsCount() {
        return checkListsCount;
    }

    public void setCheckListsCount(AtomicInteger checkListsCount) {
        this.checkListsCount = checkListsCount;
    }

    public HostBlacklistsDataSourceFacade getFacade() {
        return facade;
    }

    public void setFacade(HostBlacklistsDataSourceFacade facade) {
        this.facade = facade;
    }
}
