// name: Chat.Chatting$$ChannelListRecieved
// entry: 01d2b128

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ChannelListRecieved(long param_1,long param_2)

{
  long lVar1;
  int iVar2;
  uint uVar3;
  ulong uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  int iVar10;
  long lVar11;
  
  if ((bRam00000000049fcf19 & 1) == 0) {
    func_0x010c65d4(0x25b0);
    bRam00000000049fcf19 = 1;
  }
  lVar9 = *(long *)(param_1 + 0x30);
  if (lVar9 == 0) {
    lVar9 = func_0x010fdda8(_class_System_Collections_Generic_List<Room>_TypeInfo);
    System_Collections_Generic_List<tk2dUILayoutItem>___ctor
              (lVar9,_mm_Method_System_Collections_Generic_List<Room>__ctor__);
    *(long *)(param_1 + 0x30) = lVar9;
    if (lVar9 == 0) goto code_r0x01d2b1b8;
  }
  System_Collections_Generic_List<tk2dUICamera>__Clear
            (lVar9,_mm_Method_System_Collections_Generic_List<Room>_Clear__);
code_r0x01d2b1b8:
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isWatchingBattle(0,0);
  if (((uVar4 & 1) != 0) && (lVar9 = *(long *)(param_1 + 0x38), lVar9 != 0)) {
    lVar11 = *(long *)(param_1 + 0x30);
    if (lVar11 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUILayoutItem>__Add
              (lVar11,lVar9,_mm_Method_System_Collections_Generic_List<Room>_Add__);
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = LitJson_JsonData__get_Count(param_2,0);
  if (0 < iVar2) {
    iVar10 = 0;
    do {
      lVar9 = func_0x010fdda8(_class_Chat_Room_TypeInfo);
      Chat_Room___ctor();
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = LitJson_JsonData__get_Item(param_2,iVar10,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      Chat_Room__Deserialize(lVar9,uVar5);
      lVar11 = *(long *)(param_1 + 0x30);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = System_Collections_Generic_List<Ai>__Contains
                        (lVar11,lVar9,_mm_Method_System_Collections_Generic_List<Room>_Contains__);
      if ((uVar3 & 1) == 0) {
        lVar11 = *(long *)(param_1 + 0x30);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_List<tk2dUILayoutItem>__Add
                  (lVar11,lVar9,_mm_Method_System_Collections_Generic_List<Room>_Add__);
      }
      iVar10 = iVar10 + 1;
    } while (iVar2 != iVar10);
  }
  uVar4 = Chat_Chatting__get_IsConnected(param_1);
  if ((uVar4 & 1) != 0) {
    Chat_Chatting__GetEmptyChannel(param_1);
    lVar9 = *(long *)(param_1 + 0x48);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = *(undefined8 *)(lVar9 + 0x30);
    if ((bRam00000000049fcf1a & 1) == 0) {
      func_0x010c65d4(0x25d5);
      bRam00000000049fcf1a = 1;
    }
    if (*(char *)(param_1 + 0x80) != '\0') {
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar3 = System_String__IsNullOrEmpty(0,uVar5,0);
      if ((uVar3 & 1) == 0) {
        lVar9 = *(long *)(param_1 + 0x48);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = *(long *)(lVar9 + 0x28);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar3 = System_Collections_Generic_List<Ai>__Contains
                          (lVar9,uVar5,_mm_Method_System_Collections_Generic_List<string>_Contains__
                          );
        if ((uVar3 & 1) == 0) {
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          lVar9 = *(long *)(lVar9 + 0x28);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_List<tk2dUILayoutItem>__Add
                    (lVar9,uVar5,_mm_Method_System_Collections_Generic_List<string>_Add__);
          lVar9 = *(long *)(param_1 + 0x68);
          if (((*(byte *)(_class_System_StringComparer_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_System_StringComparer_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar6 = System_StringComparer__get_Ordinal(0,0);
          uVar7 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<string>_TypeInfo);
          System_Collections_Generic_HashSet<string>___ctor
                    (uVar7,uVar6,_mm_Method_System_Collections_Generic_HashSet<string>__ctor__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<GameObject,_Stack<int>>__Add
                    (lVar9,uVar5,uVar7,
                     _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Add__
                    );
          Chat_Chatting__EmitJoinRoom(param_1,uVar5);
          Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),uVar5);
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar6 = *(undefined8 *)(lVar9 + 0x18);
          lVar9 = func_0x010fdda8(_class_Chat_ChattingUserRequest_TypeInfo);
          System_Object___ctor(lVar9,0);
          *(undefined8 *)(lVar9 + 0x10) = uVar6;
          if ((bRam00000000049fcf28 & 1) == 0) {
            func_0x010c65d4(0x25bc);
            bRam00000000049fcf28 = 1;
          }
          if (*(char *)(param_1 + 0x80) != '\0') {
            lVar11 = *(long *)(param_1 + 0x20);
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar6 = LitJson_JsonMapper__ToJson(0,lVar9,0);
            lVar9 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
            System_Object___ctor(lVar9,0);
            *(undefined4 *)(lVar9 + 0x10) = 2;
            *(undefined8 *)(lVar9 + 0x18) = uVar6;
            *(undefined8 *)(lVar9 + 0x20) = uVar5;
            *(undefined4 *)(lVar9 + 0x28) = 0xffffffff;
            uVar5 = LitJson_JsonMapper__ToJson(0,lVar9,0);
            if (lVar11 == 0) {
              func_0x010eec64(0);
            }
            uVar6 = _str_broadcastTo;
            if ((bRam0000000004a00249 & 1) == 0) {
              func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar5,0);
              bRam0000000004a00249 = 1;
            }
            if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar5 = System_String__Format(0,_str____0____1__,uVar6,uVar5,0);
            if ((bRam0000000004a0024d & 1) == 0) {
              func_0x010c65d4(0xc5ce);
              bRam0000000004a0024d = 1;
            }
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar6 = LitJson_JsonMapper__ToObject(0,uVar5,0);
            lVar8 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
            uVar5 = _str__;
            System_Object___ctor(lVar8,0);
            *(undefined4 *)(lVar8 + 0x14) = 2;
            *(undefined4 *)(lVar8 + 0x18) = 0;
            *(undefined8 *)(lVar8 + 0x30) = uVar6;
            *(undefined8 *)(lVar8 + 0x20) = uVar5;
            *(undefined4 *)(lVar8 + 0x28) = 0xffffffff;
            *(undefined4 *)(lVar8 + 0x10) = 4;
            lVar9 = lVar11;
            if ((bRam0000000004a0024f & 1) == 0) {
              lVar9 = func_0x010c65d4(0xc5cf);
              bRam0000000004a0024f = 1;
            }
            lVar1 = *(long *)(lVar11 + 0x48);
            if (*(long *)(lVar11 + 0x50) == 0) {
              lVar9 = func_0x010eec64(0);
            }
            uVar5 = SocketIO_Encoder__Encode(lVar9,lVar8);
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
  return;
}


