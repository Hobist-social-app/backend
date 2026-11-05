package hobist.backend.modules.authentication.application.interfaces;

public interface TokenBasedAuthenticationService extends AuthenticationService{

    // M.G: maybe T dto?
    String generateToken()
}
