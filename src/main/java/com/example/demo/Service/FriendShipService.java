package com.example.demo.Service;

import com.example.demo.Custom.FriendShipCusTomResponse;
import com.example.demo.Entity.FriendshipEntity;
import com.example.demo.Entity.RoomEntity;
import com.example.demo.IService.IFriendShip;
import com.example.demo.IService.IUser;
import com.example.demo.Repositories.FriendShipRepository;
import com.example.demo.Repositories.RoomRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Request.FriendRequest;
import com.example.demo.Response.FriendShipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
public class FriendShipService implements IFriendShip {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendShipRepository friendShipRepository;
    @Autowired
    FriendShipCusTomResponse friendShipCusTomResponse;
    @Autowired
    RoomRepository roomRepository;
    @Override
    public FriendShipResponse saveFriendShip(FriendRequest friendRequest) {
        RoomEntity room=roomRepository.save(new RoomEntity("chat"));
        UserEntity receiver=userRepository.findById(friendRequest.getReceiverId()).get();
        UserEntity sender=userRepository.findById(friendRequest.getSenderId()).get();
        FriendshipEntity friendshipEntity=new FriendshipEntity();
        friendshipEntity.setUser(sender);
        friendshipEntity.setFriend(receiver);
        friendshipEntity.setRoom(room);
        FriendshipEntity f=friendShipRepository.save(friendshipEntity);
        return FriendShipResponse.builder().id(f.getId()).user(f.getFriend()).createAt(f.getCreateAt()).status(f.getStatus()).room(f.getRoom()).build();
    }
    @Override
    public Collection<FriendshipEntity> getRequestAddFriend(long idUSer) {
        return friendShipCusTomResponse.getUserSendRequest(idUSer);
    }

    @Override
    public FriendshipEntity acceptRequestAddFriend(long idFs) {
        Optional<FriendshipEntity> f = friendShipRepository.findById(idFs);
        FriendshipEntity friend = f.get();
        friend.setStatus(1);
        return friendShipRepository.save(friend);
    }

    @Transactional
    @Override
    public FriendshipEntity delRequestAddFriend(long idFs) {
        Optional<FriendshipEntity> f = friendShipRepository.findById(idFs);
        friendShipRepository.delete(f.get());
        return f.get();
    }

    @Override
    public Collection<FriendShipResponse> getAllFriend(long idUSer) {
        Collection<FriendShipResponse> friends = new ArrayList<>();
        friendShipCusTomResponse.getAllFriend(idUSer).stream().forEach(item -> {
            FriendShipResponse f = new FriendShipResponse();
            f.setRoom(item.getRoom());
            f.setId(item.getId());
            f.setCreateAt(item.getCreateAt());
            f.setStatus(item.getStatus());
            if (!(item.getUser().getId() == idUSer)) {
                f.setUser(item.getUser());
            } else {
                f.setUser(item.getFriend());
            }
            friends.add(f);
        });
        return friends;
    }


}
