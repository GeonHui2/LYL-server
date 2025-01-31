package GDG.backend.domain.user.presentation.dto.response;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.vo.UserInfoVO;

public record SignUpResponse(
        String email,
        String name,
        OauthServerType oauthServerType,
        Boolean isFirst
) {
    public SignUpResponse(UserInfoVO userInfoVO, Boolean status) {
        this(userInfoVO.email(), userInfoVO.name(), userInfoVO.oauthServerType(), status);
    }
}
