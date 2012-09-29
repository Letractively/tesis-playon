package tesis.playon.restful.util;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Long, Timestamp> {

    @Override
    public Long marshal(Timestamp timestamp) throws Exception {
	return timestamp.getTime();
    }

    @Override
    public Timestamp unmarshal(Long milis) throws Exception {
	return new Timestamp(milis);
    }
}