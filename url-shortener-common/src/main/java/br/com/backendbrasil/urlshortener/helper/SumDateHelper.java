package br.com.backendbrasil.urlshortener.helper;

import java.io.Serializable;
import java.util.Date;

public interface SumDateHelper extends Serializable {
	
	Date sum(Date date, int date2, int value);

}
