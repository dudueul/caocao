// name: Chat.Chatting$$OnGuildJoin
// entry: 01d313e4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnGuildJoin(long param_1,undefined8 param_2)

{
  long lVar1;
  uint uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  ulong uVar5;
  undefined8 uVar6;
  long lVar7;
  long lVar8;
  long lVar9;
  
  if ((bRam00000000049fcf5c & 1) == 0) {
    func_0x010c65d4(0x25ea);
    bRam00000000049fcf5c = 1;
  }
  if (((*(byte *)(_class_HttpRequest_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_HttpRequest_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = HttpRequest__CommonCheck(0,param_2,0);
  if ((uVar5 & 1) != 0) {
    if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = GuildManager__get_isApplicant(0,0);
    if ((uVar2 & 1) == 0) {
      if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = GuildManager__get_myGuildId(0,0);
      uVar6 = Chat_Chatting__GetGuildRoomId(uVar6,uVar6);
      if ((bRam00000000049fcf1a & 1) == 0) {
        func_0x010c65d4(0x25d5);
        bRam00000000049fcf1a = 1;
      }
      if (*(char *)(param_1 + 0x80) != '\0') {
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar2 = System_String__IsNullOrEmpty(0,uVar6,0);
        if ((uVar2 & 1) == 0) {
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          lVar9 = *(long *)(lVar9 + 0x28);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = System_Collections_Generic_List<Ai>__Contains
                            (lVar9,uVar6,
                             _mm_Method_System_Collections_Generic_List<string>_Contains__);
          if ((uVar2 & 1) == 0) {
            lVar9 = *(long *)(param_1 + 0x48);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            lVar9 = *(long *)(lVar9 + 0x28);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_List<tk2dUILayoutItem>__Add
                      (lVar9,uVar6,_mm_Method_System_Collections_Generic_List<string>_Add__);
            lVar9 = *(long *)(param_1 + 0x68);
            if (((*(byte *)(_class_System_StringComparer_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_StringComparer_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar3 = System_StringComparer__get_Ordinal(0,0);
            uVar4 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<string>_TypeInfo);
            System_Collections_Generic_HashSet<string>___ctor
                      (uVar4,uVar3,_mm_Method_System_Collections_Generic_HashSet<string>__ctor__);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_Dictionary<GameObject,_Stack<int>>__Add
                      (lVar9,uVar6,uVar4,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Add__
                      );
            Chat_Chatting__EmitJoinRoom(param_1,uVar6);
            Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),uVar6);
            lVar9 = *(long *)(param_1 + 0x48);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            uVar3 = *(undefined8 *)(lVar9 + 0x18);
            lVar9 = func_0x010fdda8(_class_Chat_ChattingUserRequest_TypeInfo);
            System_Object___ctor(lVar9,0);
            *(undefined8 *)(lVar9 + 0x10) = uVar3;
            if ((bRam00000000049fcf28 & 1) == 0) {
              func_0x010c65d4(0x25bc);
              bRam00000000049fcf28 = 1;
            }
            if (*(char *)(param_1 + 0x80) != '\0') {
              lVar8 = *(long *)(param_1 + 0x20);
              if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar3 = LitJson_JsonMapper__ToJson(0,lVar9,0);
              lVar9 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
              System_Object___ctor(lVar9,0);
              *(undefined4 *)(lVar9 + 0x10) = 2;
              *(undefined8 *)(lVar9 + 0x18) = uVar3;
              *(undefined8 *)(lVar9 + 0x20) = uVar6;
              *(undefined4 *)(lVar9 + 0x28) = 0xffffffff;
              uVar6 = LitJson_JsonMapper__ToJson(0,lVar9,0);
              if (lVar8 == 0) {
                func_0x010eec64(0);
              }
              uVar3 = _str_broadcastTo;
              if ((bRam0000000004a00249 & 1) == 0) {
                func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar6,0);
                bRam0000000004a00249 = 1;
              }
              if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar6 = System_String__Format(0,_str____0____1__,uVar3,uVar6,0);
              if ((bRam0000000004a0024d & 1) == 0) {
                func_0x010c65d4(0xc5ce);
                bRam0000000004a0024d = 1;
              }
              if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar3 = LitJson_JsonMapper__ToObject(0,uVar6,0);
              lVar7 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
              uVar6 = _str__;
              System_Object___ctor(lVar7,0);
              *(undefined4 *)(lVar7 + 0x14) = 2;
              *(undefined4 *)(lVar7 + 0x18) = 0;
              *(undefined8 *)(lVar7 + 0x30) = uVar3;
              *(undefined8 *)(lVar7 + 0x20) = uVar6;
              *(undefined4 *)(lVar7 + 0x28) = 0xffffffff;
              *(undefined4 *)(lVar7 + 0x10) = 4;
              lVar9 = lVar8;
              if ((bRam0000000004a0024f & 1) == 0) {
                lVar9 = func_0x010c65d4(0xc5cf);
                bRam0000000004a0024f = 1;
              }
              lVar1 = *(long *)(lVar8 + 0x48);
              if (*(long *)(lVar8 + 0x50) == 0) {
                lVar9 = func_0x010eec64(0);
              }
              uVar6 = SocketIO_Encoder__Encode(lVar9,lVar7);
              if (lVar1 == 0) {
                func_0x010eec64(0);
              }
              WebSocketSharp_WebSocket__Send(lVar1,uVar6,0);
              return;
            }
            return;
          }
        }
      }
      return;
    }
  }
  return;
}


