package app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.entity.IDConfiguration;
import app.data.repository.reactive.IDConfigurationRepoReact;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
public class IdGeneratorUtility {

	private String id;
	
	@Autowired
	private IDConfigurationRepoReact idcrepo;
		
	public String getIdForDocument(String documentName) {
		return generateIdForDocument(documentName);
	}
	
	private String generateIdForDocument(String documentName) {
		char[] suffix = new char[7];
		IDConfiguration idConfiguration = new IDConfiguration();
		idConfiguration = idcrepo.findoneByDocumentName(documentName).block();
		String previouscount = idConfiguration.getPrevious();
		int zerocount = 7 - previouscount.length();
		for(int i = 1; i<=zerocount; i++) { 
			suffix[i] = '0'; 
			}
		//String middle = suffix.toString();
		String middle = String.valueOf(suffix).trim();
		String finalId = idConfiguration.getName()
				.concat(middle)
				.concat(Integer.toString(Integer.parseInt(previouscount)+1));
		log.info("id for table"+idConfiguration.getDocument()+" is "+finalId);
		int nextcount = Integer.parseInt(previouscount)+1;
		idConfiguration.setPrevious(String.valueOf(nextcount));
		IDConfiguration second  =  idcrepo.save(idConfiguration).block();
		log.info("next id " + second);
		return finalId;
	}
	
	public String detroyId(String documentName) {
		IDConfiguration idConfiguration = new IDConfiguration();
		idConfiguration = idcrepo.findoneByDocumentName(documentName).block();
		int previouscount = Integer.parseInt(idConfiguration.getPrevious());
		idConfiguration.setPrevious(String.valueOf(previouscount-1));
		return idcrepo.save(idConfiguration).block().getPrevious();
	}
}
