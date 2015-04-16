package ro.fortech.access;

//import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

import ro.fortech.model.Movie;
import ro.fortech.utils.Constants;

public class MovieAccess {

	Node node;
	Client client;

	String index;
	String type;
	private Properties properties = new Properties();

	public MovieAccess() {

	}

	public void init(Properties properties) {
		this.properties = properties;
		this.node = nodeBuilder().clusterName(
				properties.getProperty(Constants.CLUSTER)).node();
		this.client = node.client();
	}

	public Map<String, Object> createJsonDocument(Movie movie) {

		Map<String, Object> jsonDocument = new HashMap<String, Object>();

		jsonDocument.put("title", movie.getTitle());
		jsonDocument.put("director", movie.getDirector());
		jsonDocument.put("year", movie.getYear());
		jsonDocument.put("id", movie.getId());
		jsonDocument.put("imagine", movie.getImagine());
		return jsonDocument;
	}

	public void updateDocument(Movie movie) {

		/*
		 * updateRequest updateRequest = new UpdateRequest();
		 * 
		 * updateRequest.index(properties.getProperty(Constants.INDEX));
		 * updateRequest.type(properties.getProperty(Constants.TYPE));
		 * updateRequest.id(String.valueOf(movie.getId()));
		 * updateRequest.doc(createJsonDocument(movie)); try {
		 * client.update(updateRequest).get(); } catch (InterruptedException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (ExecutionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * client.close();
		 */

	}

	public Boolean upsertDocument(Movie movie) {
		UpdateResponse ur = new UpdateResponse();

		IndexRequest indexRequest = new IndexRequest(
				properties.getProperty(Constants.INDEX),
				properties.getProperty(Constants.TYPE), String.valueOf(movie
						.getId())).source(createJsonDocument(movie));
		UpdateRequest updateRequest = new UpdateRequest(
				properties.getProperty(Constants.INDEX),
				properties.getProperty(Constants.TYPE), String.valueOf(movie
						.getId())).doc(createJsonDocument(movie)).upsert(
				indexRequest);
		try {
			ur = client.update(updateRequest).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		client.close();

		if (ur.isCreated()){
			return true;
		} else {
			return false;
		}
	}

	public Map<String, Object> getDocument(String id) {

		/*
		 * GetResponse getResponse = client
		 * .prepareGet(properties.getProperty(Constants.INDEX),
		 * properties.getProperty(Constants.TYPE), id).execute() .actionGet();
		 * Map<String, Object> source = getResponse.getSource();
		 * 
		 * System.out.println("------------------------------");
		 * System.out.println("Index: " + getResponse.getIndex());
		 * System.out.println("Type: " + getResponse.getType());
		 * System.out.println("Id: " + getResponse.getId());
		 * System.out.println("Version: " + getResponse.getVersion());
		 * System.out.println(source);
		 * System.out.println("------------------------------");
		 * 
		 * client.close();
		 * 
		 * return source;
		 */

		return new HashMap<String, Object>();
	}

	public Boolean deleteDocument(String id) {

		DeleteResponse drb = client
				.prepareDelete(properties.getProperty(Constants.INDEX),
						properties.getProperty(Constants.TYPE), id).execute()
				.actionGet();
		client.close();
		// if id of movie is found
		if (drb.isFound()) {
			return true;
		} else {
			return false;
		}

	}

	public List<Movie> searchDocument(String column, String value) {

		/*
		 * SearchResponse response = client .prepareSearch("index")
		 * .setTypes("type") .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		 * .setQuery(QueryBuilders.termQuery("multi", "test")) // Query
		 * .setPostFilter(
		 * FilterBuilders.rangeFilter("year").from(2010).to(2015)) // Filter
		 * .setFrom(0).setSize(60).setExplain(true).execute().actionGet();
		 */
		// SearchResponse response =
		// client.prepareSearch().setPostFilter(FilterBuilders.rangeFilter("year").from(2010).to(2015)).execute().actionGet();

		SearchResponse response;

		if (value == null && column == null) {
			response = client.prepareSearch()
					.setTypes(properties.getProperty(Constants.TYPE)).execute()
					.actionGet();

		} else {

			response = client.prepareSearch()
					.setTypes(properties.getProperty(Constants.TYPE))
					.setQuery(QueryBuilders.matchQuery(column, value))
					.execute().actionGet();
		}

		List<Movie> result = new ArrayList<Movie>();

		SearchHit[] results = response.getHits().getHits();
		for (SearchHit hit : results) {
			Map<String, Object> partialResult = hit.getSource();

			String localTitle = "";
			String localDirector = "";
			int localId = 0;
			int localYear = 0;
			String localImagine = "";

			for (Map.Entry<String, Object> entry : partialResult.entrySet()) {

				if (entry.getKey().equals("title")) {
					localTitle = entry.getValue().toString();
				} else if (entry.getKey().equals("director")) {
					localDirector = entry.getValue().toString();
				} else if (entry.getKey().equals("id")) {
					localId = Integer.parseInt(entry.getValue().toString());
				} else if (entry.getKey().equals("year")) {
					localYear = Integer.parseInt(entry.getValue().toString());
				} else if (entry.getKey().equals("imagine")) {
					localImagine = entry.getValue().toString();
				}
			}

			Movie movie = new Movie(localTitle, localDirector, localYear,
					localId, localImagine);

			result.add(movie);
			/*
			 * System.out.println(hit.getType());
			 * System.out.println(movie.getId() + " " + movie.getTitle() + " " +
			 * movie.getDirector() + " " + movie.getYear());
			 */

		}

		client.close();
		return result;

		// return new ArrayList<Movie>();
	}

	public List<Movie> searchDocument() {
		return searchDocument(null, null);
	}
}
