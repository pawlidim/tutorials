package de.pawlidi.tutorials.geoip;

import java.io.File;
import java.io.IOException;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;

/**
 * 
 * @author PAWLIDIM
 *
 */
public class GeoIPService {

	private static final String DATABASE_FILE_PATH = "geoip";
	private static final String DATABASE_FILE_NAME = "geoip.mmdb";

	private GeoIP geoIP;

	private GeoIPService() throws IOException {
		super();
		initialize();
	}

	private void initialize() throws IOException {
		geoIP = new GeoIP(DATABASE_FILE_PATH + File.separator + DATABASE_FILE_NAME);
	}

	public String getISOCountry(final String ip) {
		Country country;
		try {
			country = geoIP.getCountry(ip);
			return country.getIsoCode();
		} catch (IOException | GeoIp2Exception e) {
			// ignore exception
		}
		return null;
	}

	public String getCity(final String ip) {
		try {
			City city = geoIP.getCity(ip);
			return city.getName();
		} catch (IOException | GeoIp2Exception e) {
			// ignore exception

		}
		return null;
	}

	public String getPostal(final String ip) {
		try {
			Postal postal = geoIP.getPostal(ip);
			return postal.getCode();
		} catch (IOException | GeoIp2Exception e) {
			// ignore exception
		}
		return null;
	}

	public String getTimeZone(final String ip) {
		try {
			Location location = geoIP.getLocation(ip);
			return location.getTimeZone();
		} catch (IOException | GeoIp2Exception e) {
			// ignore exception
		}
		return null;
	}
}
