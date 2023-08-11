package com.example.demo.IService;

import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Request.FriendRequest;
import com.example.demo.Response.FriendShipResponse;
import java.util.Collection;

public interface IFriendShip {
    FriendShipResponse saveFriendShip(FriendRequest friendRequest);
    public Collection<FriendshipEntity> getRequestAddFriend(long idUSer) ;
    public FriendshipEntity acceptRequestAddFriend(long idFS);
    public FriendshipEntity delRequestAddFriend(long idFS);
    public Collection<FriendShipResponse> getAllFriend(long idUSer);

}
