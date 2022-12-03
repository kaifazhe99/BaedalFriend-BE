package com.hanghae.baedalfriend.Mypage.controller;

import com.hanghae.baedalfriend.Mypage.dto.request.MypageRequestDto;
import com.hanghae.baedalfriend.Mypage.dto.request.PasswordDeleteRequestDto;
import com.hanghae.baedalfriend.Mypage.service.MypageService;
import com.hanghae.baedalfriend.domain.UserDetailsImpl;
import com.hanghae.baedalfriend.dto.responsedto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class MypageController {

    private final MypageService mypageService;

    //프로필이미지 삭제
    @DeleteMapping("/mypages/image/{memberId}")
    public ResponseDto<?> deleteProfileImage(@PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.deleteProfileImage(memberId, userDetails);
    }

//    @PostMapping(value = "/auth/posts", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
//    public ResponseDto<?> createPosts(@RequestPart(value = "postDto") PostRequestDto requestDto, HttpServletRequest request,
//                                      @RequestPart(value = "imageUrl", required = false) MultipartFile multipartFile) throws IOException {
//        return postService.createPost(requestDto, request, multipartFile);
//    }
    // 이미지 변경 + 닉네임변경
    @PatchMapping(value = "/mypages/edit/{memberId}")
    public ResponseDto<?> editMember(@PathVariable Long memberId, @RequestPart(value = "nickname", required = false) MypageRequestDto requestDto,
                                     @RequestPart(value = "imgUrl", required = false) MultipartFile multipartFile,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return mypageService.editMember(memberId, requestDto, multipartFile, userDetails);
    }

    // 주소 수정
    @PutMapping("/mypages/address/{memberId}")
    public ResponseDto<?> updateAddress(@PathVariable Long memberId, @RequestBody MypageRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.updateAddress(memberId, requestDto, userDetails);
    }

    // 유저 정보 조회 (nickname. profileURL, email. address)
    @GetMapping("/mypages/info/{memberId}")
    public ResponseDto<?> getMemberInfo(@PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.getMemberInfo(memberId, userDetails);
    }

    // 내가 쓴 게시글
    @GetMapping("/mypages/posts/{memberId}")
    public ResponseDto<?> getMyPost(@PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.getMyPost(memberId, userDetails);
    }

    // 내가 들어간 채팅방
//    @GetMapping("/mypages/chat/{memberId}")
//    public ResponseDto<?> getMyChat(@PathVariable Long memberId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return mypageService.getMyChat(memberId, userDetails);
//    }

    // 비밀번호 변경
//    @PutMapping("/updatePassword")
//    public ResponseDto<?> updatePassword(@RequestBody PasswordRequestDto passwordRequestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return mypageService.updatePassword(passwordRequestDto, userDetails);
//    }

    // 회원 탈퇴
    @DeleteMapping("/withdrawal/{memberId}")
    public ResponseDto<?> withdrawal(@PathVariable Long memberId, @RequestBody PasswordDeleteRequestDto passwordDeleteRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.withdrawMember(memberId, passwordDeleteRequestDto, userDetails);
    }
}
