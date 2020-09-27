package pattern.finder.com.example.regexchecker.service;

import pattern.finder.com.example.regexchecker.dto.RegexRequest;
import pattern.finder.com.example.regexchecker.dto.RegexResponse;

public interface RegexService {
	public RegexResponse getmatches(RegexRequest request) throws Exception;
}
