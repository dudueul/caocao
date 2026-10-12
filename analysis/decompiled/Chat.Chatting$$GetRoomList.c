// name: Chat.Chatting$$GetRoomList
// entry: 01d3060c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__GetRoomList(long param_1)

{
  long lVar1;
  undefined8 uVar2;
  long *plVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  undefined4 uStack_48;
  undefined1 uStack_41;
  
  if ((bRam00000000049fcf52 & 1) == 0) {
    func_0x010c65d4(0x25d0);
    bRam00000000049fcf52 = 1;
  }
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<HttpRequest>_get_instance__)
  ;
  uVar5 = _mm_Method_Chat_Chatting_OnChatRoomListRecieved__;
  uVar2 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
  HttpRequest_ResponseCallback___ctor(uVar2,param_1,uVar5,0);
  plVar3 = (long *)func_0x01101ad4(_class_object___TypeInfo,4);
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  if ((_str_want_room_id != 0) &&
     (lVar4 = func_0x010fdfcc(_str_want_room_id,*(undefined8 *)(*plVar3 + 0x40)), lVar4 == 0)) {
    uVar5 = func_0x010efe90();
    func_0x010eebec(uVar5,0,0);
  }
  lVar4 = _str_want_room_id;
  if ((int)plVar3[3] == 0) {
    uVar5 = func_0x010ef850();
    func_0x010eebec(uVar5,0,0);
  }
  plVar3[4] = lVar4;
  uStack_41 = 1;
  lVar4 = func_0x010fdd08(_class_bool_TypeInfo,&uStack_41);
  if ((lVar4 != 0) && (lVar6 = func_0x010fdfcc(lVar4,*(undefined8 *)(*plVar3 + 0x40)), lVar6 == 0))
  {
    uVar5 = func_0x010efe90();
    func_0x010eebec(uVar5,0,0);
  }
  if (*(uint *)(plVar3 + 3) < 2) {
    uVar5 = func_0x010ef850();
    func_0x010eebec(uVar5,0,0);
  }
  plVar3[5] = lVar4;
  if ((_str_room_type != 0) &&
     (lVar4 = func_0x010fdfcc(_str_room_type,*(undefined8 *)(*plVar3 + 0x40)), lVar4 == 0)) {
    uVar5 = func_0x010efe90();
    func_0x010eebec(uVar5,0,0);
  }
  lVar4 = _str_room_type;
  if (*(uint *)(plVar3 + 3) < 3) {
    uVar5 = func_0x010ef850();
    func_0x010eebec(uVar5,0,0);
  }
  plVar3[6] = lVar4;
  lVar4 = *(long *)(param_1 + 0x48);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uStack_48 = *(undefined4 *)(lVar4 + 0x38);
  lVar4 = func_0x010fdd08(_class_Chat_EChatRoomLocale_TypeInfo,&uStack_48);
  if ((lVar4 != 0) && (lVar6 = func_0x010fdfcc(lVar4,*(undefined8 *)(*plVar3 + 0x40)), lVar6 == 0))
  {
    uVar5 = func_0x010efe90();
    func_0x010eebec(uVar5,0,0);
  }
  if (*(uint *)(plVar3 + 3) < 4) {
    uVar5 = func_0x010ef850();
    func_0x010eebec(uVar5,0,0);
  }
  plVar3[7] = lVar4;
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  HttpRequest__Send(lVar1,_str__chat_getRoomList,_str_POST,0,uVar2,plVar3,0);
  return;
}


