// name: Chat.Chatting$$OnChatBlockListRecieved
// entry: 01d312f0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChatBlockListRecieved(long param_1,long param_2)

{
  undefined8 uVar1;
  long *plVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  ulong uVar6;
  long lVar7;
  undefined4 uStack_48;
  undefined1 uStack_41;
  
  if ((bRam00000000049fcf59 & 1) == 0) {
    func_0x010c65d4(0x25dd);
    bRam00000000049fcf59 = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar6 & 1) != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = LitJson_JsonData__get_Item(param_2,_str_packet,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = LitJson_JsonData__get_Item(lVar7,_str_ChatBlockList,0);
    if (lVar7 != 0) {
      Chat_Chatting__DeserializeBlockUsers(param_1,lVar7);
      if ((bRam00000000049fcf52 & 1) == 0) {
        func_0x010c65d4(0x25d0);
        bRam00000000049fcf52 = 1;
      }
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar7 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar4 = _mm_Method_Chat_Chatting_OnChatRoomListRecieved__;
      uVar1 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar1,param_1,uVar4,0);
      plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,4);
      if (plVar2 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_want_room_id != 0) &&
         (lVar3 = func_0x010fdfcc(_str_want_room_id,*(undefined8 *)(*plVar2 + 0x40)), lVar3 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar3 = _str_want_room_id;
      if ((int)plVar2[3] == 0) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar2[4] = lVar3;
      uStack_41 = 1;
      lVar3 = func_0x010fdd08(_class_bool_TypeInfo,&uStack_41);
      if ((lVar3 != 0) &&
         (lVar5 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar5 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar2 + 3) < 2) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar2[5] = lVar3;
      if ((_str_room_type != 0) &&
         (lVar3 = func_0x010fdfcc(_str_room_type,*(undefined8 *)(*plVar2 + 0x40)), lVar3 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      lVar3 = _str_room_type;
      if (*(uint *)(plVar2 + 3) < 3) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar2[6] = lVar3;
      lVar3 = *(long *)(param_1 + 0x48);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      uStack_48 = *(undefined4 *)(lVar3 + 0x38);
      lVar3 = func_0x010fdd08(_class_Chat_EChatRoomLocale_TypeInfo,&uStack_48);
      if ((lVar3 != 0) &&
         (lVar5 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar5 == 0)) {
        uVar4 = func_0x010efe90();
        func_0x010eebec(uVar4,0,0);
      }
      if (*(uint *)(plVar2 + 3) < 4) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      plVar2[7] = lVar3;
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      HttpRequest__Send(lVar7,_str__chat_getRoomList,_str_POST,0,uVar1,plVar2,0);
      return;
    }
  }
  return;
}


