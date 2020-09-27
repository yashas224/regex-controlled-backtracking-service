package pattern.finder.com.example.regexchecker.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import pattern.finder.com.example.regexchecker.dto.RegexRequest;
import pattern.finder.com.example.regexchecker.dto.RegexResponse;
import pattern.finder.com.example.regexchecker.service.RegexService;

@RestController
@RequestMapping("/match")
public class RegexController {

	@Autowired
	@Qualifier("manage-backtracking")
	RegexService regexServiceWithTimeOut;

	@Autowired
	RegexService regexService;

	// Asynchronous work
	@PostMapping("/match-with-timeout")
	public WebAsyncTask<RegexResponse> getMatchWithTimeOut(@RequestBody RegexRequest request) throws Exception {
		Callable<RegexResponse> callable = () -> {
			RegexResponse response = regexServiceWithTimeOut.getmatches(request);
			System.out.println(String.format("/aysncTask/ for regex %s is called for  thread with name : %s",
					request.getRegex(), Thread.currentThread().getName()));
			return response;
		};
		return new WebAsyncTask<RegexResponse>(callable);

	}

	// Asynchronous work
	@PostMapping("/match-without-timeout")
	public WebAsyncTask<RegexResponse> getMatch(@RequestBody RegexRequest request) throws Exception {
		Callable<RegexResponse> callable = () -> {
			RegexResponse response = regexService.getmatches(request);
			System.out.println(String.format("/aysncTask/ for regex %s is called for  thread with name : %s",
					request.getRegex(), Thread.currentThread().getName()));
			return response;
		};
		return new WebAsyncTask<RegexResponse>(callable);

	}

}
