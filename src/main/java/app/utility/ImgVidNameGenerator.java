package app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.entity.ImageCount;
import app.data.repository.reactive.ImageCountRepoReact;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImgVidNameGenerator {
	
	@Autowired private ImageCountRepoReact imgrepo;

	public String getImgName(String userId, String postId) {
		return this.generateImgName(userId,postId);
	}

	private String generateImgName(String userId, String postId) {
		char[] suffix = new char[7];
		ImageCount idConfiguration = new ImageCount();
		idConfiguration = imgrepo.findoneByName("img").block();
		String previouscount = idConfiguration.getPrevious();
		int zerocount = 7 - previouscount.length();
		for(int i = 1; i<=zerocount; i++) { 
			suffix[i] = '0'; 
			}
		//String middle = suffix.toString();
		String middle = String.valueOf(suffix).trim();
		String finalId = idConfiguration.getName()
				.concat("-"+userId)
				.concat("-"+postId)
				.concat("-"+middle)
				.concat(Integer.toString(Integer.parseInt(previouscount)+1));
		log.info("id for image"+idConfiguration.getName()+" is "+finalId);
		int nextcount = Integer.parseInt(previouscount)+1;
		idConfiguration.setPrevious(String.valueOf(nextcount));
		ImageCount second  =  imgrepo.save(idConfiguration).block();
		log.info("next id " + second);
		return finalId;
	}
}
