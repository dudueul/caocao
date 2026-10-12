// name: Chat.Chatting$$ChangeUserName
// entry: 01d2cf24

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ChangeUserName(long param_1,undefined8 param_2)

{
  long lVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  
  lVar7 = *(long *)(param_1 + 0x48);
  lVar4 = param_1;
  if (lVar7 == 0) {
    lVar4 = func_0x010eec64(0);
  }
  uVar5 = Chat_Chatting__GetSubRoomId(lVar4,*(undefined8 *)(lVar7 + 0x18));
  Chat_Chatting__LeaveFromChannel(param_1,uVar5);
  lVar4 = *(long *)(param_1 + 0x48);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar4 + 0x18) = param_2;
  if ((bRam00000000049fcf1a & 1) == 0) {
    func_0x010c65d4(0x25d5);
    bRam00000000049fcf1a = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__IsNullOrEmpty(0,param_2,0);
    if ((uVar2 & 1) == 0) {
      lVar4 = *(long *)(param_1 + 0x48);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      lVar4 = *(long *)(lVar4 + 0x28);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = System_Collections_Generic_List<Ai>__Contains
                        (lVar4,param_2,_mm_Method_System_Collections_Generic_List<string>_Contains__
                        );
      if ((uVar2 & 1) == 0) {
        lVar4 = *(long *)(param_1 + 0x48);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        lVar4 = *(long *)(lVar4 + 0x28);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_List<tk2dUILayoutItem>__Add
                  (lVar4,param_2,_mm_Method_System_Collections_Generic_List<string>_Add__);
        lVar4 = *(long *)(param_1 + 0x68);
        if (((*(byte *)(_class_System_StringComparer_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_System_StringComparer_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar5 = System_StringComparer__get_Ordinal(0,0);
        uVar3 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<string>_TypeInfo);
        System_Collections_Generic_HashSet<string>___ctor
                  (uVar3,uVar5,_mm_Method_System_Collections_Generic_HashSet<string>__ctor__);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_Dictionary<GameObject,_Stack<int>>__Add
                  (lVar4,param_2,uVar3,
                   _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Add__);
        Chat_Chatting__EmitJoinRoom(param_1,param_2);
        Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),param_2);
        lVar4 = *(long *)(param_1 + 0x48);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        uVar5 = *(undefined8 *)(lVar4 + 0x18);
        lVar4 = func_0x010fdda8(_class_Chat_ChattingUserRequest_TypeInfo);
        System_Object___ctor(lVar4,0);
        *(undefined8 *)(lVar4 + 0x10) = uVar5;
        if ((bRam00000000049fcf28 & 1) == 0) {
          func_0x010c65d4(0x25bc);
          bRam00000000049fcf28 = 1;
        }
        if (*(char *)(param_1 + 0x80) != '\0') {
          lVar7 = *(long *)(param_1 + 0x20);
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar5 = LitJson_JsonMapper__ToJson(0,lVar4,0);
          lVar4 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
          System_Object___ctor(lVar4,0);
          *(undefined4 *)(lVar4 + 0x10) = 2;
          *(undefined8 *)(lVar4 + 0x18) = uVar5;
          *(undefined8 *)(lVar4 + 0x20) = param_2;
          *(undefined4 *)(lVar4 + 0x28) = 0xffffffff;
          uVar5 = LitJson_JsonMapper__ToJson(0,lVar4,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = _str_broadcastTo;
          if ((bRam0000000004a00249 & 1) == 0) {
            func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar5,0);
            bRam0000000004a00249 = 1;
          }
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar5 = System_String__Format(0,_str____0____1__,uVar3,uVar5,0);
          if ((bRam0000000004a0024d & 1) == 0) {
            func_0x010c65d4(0xc5ce);
            bRam0000000004a0024d = 1;
          }
          if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar3 = LitJson_JsonMapper__ToObject(0,uVar5,0);
          lVar6 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
          uVar5 = _str__;
          System_Object___ctor(lVar6,0);
          *(undefined4 *)(lVar6 + 0x14) = 2;
          *(undefined4 *)(lVar6 + 0x18) = 0;
          *(undefined8 *)(lVar6 + 0x30) = uVar3;
          *(undefined8 *)(lVar6 + 0x20) = uVar5;
          *(undefined4 *)(lVar6 + 0x28) = 0xffffffff;
          *(undefined4 *)(lVar6 + 0x10) = 4;
          lVar4 = lVar7;
          if ((bRam0000000004a0024f & 1) == 0) {
            lVar4 = func_0x010c65d4(0xc5cf);
            bRam0000000004a0024f = 1;
          }
          lVar1 = *(long *)(lVar7 + 0x48);
          if (*(long *)(lVar7 + 0x50) == 0) {
            lVar4 = func_0x010eec64(0);
          }
          uVar5 = SocketIO_Encoder__Encode(lVar4,lVar6);
          if (lVar1 == 0) {
            func_0x010eec64(0);
          }
          WebSocketSharp_WebSocket__Send(lVar1,uVar5,0);
          return;
        }
        return;
      }
    }
  }
  return;
}


