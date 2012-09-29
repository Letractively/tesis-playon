package tesis.playon.restful.util;

import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<Long, Date> {

    @Override
    public Long marshal(Date date) throws Exception {
	return date.getTime();
    }

    @Override
    public Date unmarshal(Long milis) throws Exception {
	return new Date(milis);
    }
}