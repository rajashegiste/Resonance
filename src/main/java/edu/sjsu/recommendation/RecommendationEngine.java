package edu.sjsu.recommendation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommendationEngine {

	public List<RecommendedItem> getUserBasedRecommendation(long userid, int numOfRecommendation) {
		List<RecommendedItem> recommendations = null;;
		DataModel model = null;
		try {
			model = new FileDataModel(
					new File("C:/Users/rajas/Documents/workspace-sts-3.7.3.RELEASE/Data/mydata.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserSimilarity similarity = null;
		try {
			similarity = new PearsonCorrelationSimilarity(model);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

		try {
			recommendations = recommender.recommend(userid, numOfRecommendation);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}

		return recommendations;
	}

}
