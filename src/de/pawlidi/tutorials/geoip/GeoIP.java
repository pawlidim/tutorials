package de.pawlidi.tutorials.geoip;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

/**
 * This class implemets geo ip methods.
 * 
 * @author PAWLIDIM
 *
 */
public final class GeoIP implements Serializable {

	private DatabaseReader databaseReader;

	/**
	 * Default constructor to construct new geo ip object for given database
	 * file.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public GeoIP(File file) throws IOException {
		this.databaseReader = new DatabaseReader.Builder(file).build();
	}

	/**
	 * Default constructor to construct new geo ip object for given path for
	 * database.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public GeoIP(final String path) throws IOException {
		this(new File(path));
	}

	/**
	 * Default constuctor to construct new geo ip for given database reader.
	 * 
	 * @param databaseReader
	 */
	public GeoIP(DatabaseReader databaseReader) {
		super();
		this.databaseReader = databaseReader;
	}

	/**
	 * Return the geo informations for given ip.
	 * 
	 * @param ip
	 * @return geoInformations
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public CityResponse getCityResponse(final String ip) throws IOException, GeoIp2Exception {
		InetAddress ipAddress = InetAddress.getByName(ip);
		return databaseReader.city(ipAddress);
	}

	/**
	 * Return geo country information for given ip.
	 * 
	 * @param ip
	 * @return geoCountry
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public Country getCountry(final String ip) throws IOException, GeoIp2Exception {
		CityResponse response = getCityResponse(ip);
		return response.getCountry();
	}

	/**
	 * Return geo subdivision information for given ip.
	 * 
	 * @param ip
	 * @return geoSubdivision
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public Subdivision getSubdivision(final String ip) throws IOException, GeoIp2Exception {
		CityResponse response = getCityResponse(ip);
		return response.getMostSpecificSubdivision();
	}

	/**
	 * Return geo city informations for given ip.
	 * 
	 * @param ip
	 * @return geoCity
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public City getCity(final String ip) throws IOException, GeoIp2Exception {
		CityResponse response = getCityResponse(ip);
		return response.getCity();
	}

	/**
	 * Return geo postal information for given ip.
	 * 
	 * @param ip
	 * @return geoPostal
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public Postal getPostal(final String ip) throws IOException, GeoIp2Exception {
		CityResponse response = getCityResponse(ip);
		return response.getPostal();
	}

	/**
	 * Returns geo location for given ip.
	 * 
	 * @param ip
	 * @return geoLocation
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public Location getLocation(final String ip) throws IOException, GeoIp2Exception {
		CityResponse response = getCityResponse(ip);
		return response.getLocation();
	}

	/**
	 * Close database reader.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		databaseReader.close();
	}
}
