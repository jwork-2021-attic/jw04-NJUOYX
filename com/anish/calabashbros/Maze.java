package com.anish.calabashbros;

import mazegen.Pump;

public interface Maze <T extends Pump>{
    public interface Status{
        
        public void setFirstDirection();

        public Boolean nextDierection();

        public Boolean oneWay();

        public Boolean noWay();

        public String logOut();
    }

    public Status getEntry();

    public Boolean isExit(Status s);

    public void setStatus(Status s);
    //set all the infos for next step
    public Status nextStep(Status s);
    //return log
}


