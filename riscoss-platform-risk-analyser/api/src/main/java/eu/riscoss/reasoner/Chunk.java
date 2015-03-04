package eu.riscoss.reasoner;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Generic fragment of information to be passed to and from the platform;
 * can be for example a node of a graph, or a relation, or a weight, or a record, or whatever else
 * 
 * @author albertosiena
 *
 */
public class Chunk {
	
	Address		address = null;
	
	String		id;
	
	/*
	 *  Additional qualifier that represents the "type" of information this chunk is representing
	 *  if not needed, can be ignored by the engine
	 */
	String		stereotype;
	
	public Chunk() {}
	
	public Chunk( String id ) {
		this.id = id;
	}
	
	public Chunk(String id, String stereotype ) {
		this( id );
		this.stereotype = stereotype;
	}

	public Chunk( Address addr, String id, String stereotype ) {
		this( id );
		this.stereotype = stereotype;
		this.address = addr;
	}

	public String getId() {
		return this.id;
	}
	
	public String getStereotype() {
		return stereotype;
	}
	
	public Address getAddress() {
		return address;
	}
	
	String toUrl() {
		return "addr=" + address + "id=" + id + "&stereotype=" + stereotype;
	}
	
	public String toString() {
		return toUrl();
	}
	
	public Chunk fromUrl( String url ) {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		try
		{
			String[] pairs = url.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
			Chunk c = new Chunk( query_pairs.get( "id" ) );
			c.stereotype = query_pairs.get( "stereotype" );
			return c;
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			return null;
		}
	}
}