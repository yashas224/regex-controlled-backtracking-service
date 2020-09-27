package pattern.finder.com.example.regexchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pattern.finder.com.example.regexchecker.dto.RegexRequest;
import pattern.finder.com.example.regexchecker.dto.RegexResponse;
import pattern.finder.com.example.regexchecker.util.RegularExpressionUtils;

@Service("manage-backtracking")
public class RegexManageBacktracking implements RegexService {
	@Value("${timeOut}")
	private String timeOut;
	@Autowired
	RegularExpressionUtils manageBackTrackingUtil;

	@Override
	public RegexResponse getmatches(RegexRequest request) throws Exception {
		RegexResponse resopnse = new RegexResponse();
		String match = "";
		try {
			match = manageBackTrackingUtil.getMatch(request.getTextBody(), Integer.parseInt(timeOut), request.getRegex());
			resopnse.setError(false);
			resopnse.setMatch(match);
		} catch (Exception e) {
			throw new Exception();
		}

		return resopnse;
	}

}
