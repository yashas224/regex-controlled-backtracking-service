package pattern.finder.com.example.regexchecker.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pattern.finder.com.example.regexchecker.dto.RegexRequest;
import pattern.finder.com.example.regexchecker.dto.RegexResponse;

@Service
@Primary
public class RegexServiceImpl implements RegexService {

	@Override
	public RegexResponse getmatches(RegexRequest request) {
		RegexResponse resopnse = new RegexResponse();
		Pattern pattern = null;
		Matcher matcher;
		try {
			pattern = Pattern.compile(request.getRegex());
		} catch (Exception e) {
			resopnse.setError(true);
		}

		matcher = pattern.matcher(request.getTextBody());

		if (matcher.find()) {
			System.out.println("text Found " + matcher.group() + " starting at index " + matcher.start()
					+ " and ending at index " + matcher.end());
			String match = matcher.group();
			resopnse.setMatch(match);
			resopnse.setError(false);
		}
		return resopnse;

	}

}
