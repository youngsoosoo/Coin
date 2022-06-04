package com.example.coinproject.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import com.example.coinproject.entity.KakaoPayApprovalVO;
import com.example.coinproject.entity.KakaoPayVO;
import com.example.coinproject.entity.coin_user;
import com.example.coinproject.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
public class kakaoPayService {
    private static final String HOST = "https://kapi.kakao.com";

    @Autowired // 객체 자동 연결
    private RegisterRepository registerRepository;
    private KakaoPayVO kakaoPayVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(int coin, String userid) {

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3ef51cabada59b06583801b742c8ec3b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");            // 가맹점 코드
        params.add("partner_order_id", "1");     // 가맹점 주문 번호
        params.add("partner_user_id", userid);    // 가맹점 회원 아이디
        params.add("item_name", "COIN");            // 상품명
        params.add("quantity", "100");                // 상품 수량
        params.add("total_amount", String.valueOf(coin * 500));         // 상품 총액
        params.add("tax_free_amount", "100");       // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess?coin=" + coin);    // 결제 성공시
        params.add("cancel_url", "http://localhost:8080/kakaoPay");       // 결제 취소시
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");    // 결제 실패시

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVO.class);

            log.info("" + kakaoPayVO);

            if(coin < 1){
                return "http://localhost:8080/kakaoPaySuccessFail";
            }else{
                coin_user user = new coin_user();
                Optional<coin_user> result_id = registerRepository.findByUserid(userid);   //DB에서 불러온 coin_user
                user.setUserid(userid);    //userid는 바꿀 수 없다.
                user.setUsername((String) result_id.get().getUsername());
                user.setUserpw((String) result_id.get().getUserpw());
                //입력 값이 있다면 원래 있던 코인의 수와 더해준다.
                Integer sum = (Integer) result_id.get().getUsercoin() + coin;
                user.setUsercoin(sum);
                registerRepository.save(user);                  // DB에 저장해준다
            }

            return kakaoPayVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";

    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, int coin, String userid) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3ef51cabada59b06583801b742c8ec3b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayVO.getTid());
        params.add("partner_order_id", "1");
        params.add("partner_user_id", userid);
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(coin * 500));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
