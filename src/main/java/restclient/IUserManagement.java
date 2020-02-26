package restclient;

public interface IUserManagement {

	public UserDetails getUserDetails(String userId);

	public Result insertUserDetails(UserDetails usrDetails);

	public Result updateUserDetails(UserDetails userDetails);

	public Result deleteUserDetails(String userId);

	public UserDetails[] getAllUserDetails();

}
