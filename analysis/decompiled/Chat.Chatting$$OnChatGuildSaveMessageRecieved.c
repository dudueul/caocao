// name: Chat.Chatting$$OnChatGuildSaveMessageRecieved
// entry: 01d310a8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChatGuildSaveMessageRecieved(long param_1,long param_2)

{
  long lVar1;
  uint uVar2;
  int iVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  ulong uVar6;
  long lVar7;
  long lVar8;
  undefined8 uVar9;
  int iVar10;
  long lVar11;
  
  if ((bRam00000000049fcf58 & 1) == 0) {
    func_0x010c65d4(0x25de);
    bRam00000000049fcf58 = 1;
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
    lVar7 = LitJson_JsonData__get_Item(lVar7,_str_guildChatSaveMessage,0);
    if (lVar7 != 0) {
      Chat_Chatting__ClearChatMsg(param_1,3);
      iVar3 = LitJson_JsonData__get_Count(lVar7,0);
      if (0 < iVar3) {
        iVar10 = 0;
        do {
          lVar8 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
          System_Object___ctor(lVar8,0);
          uVar9 = LitJson_JsonData__get_Item(lVar7,iVar10,0);
          if (lVar8 == 0) {
            func_0x010eec64(0);
            Chat_Broadcast__Deserialize(0,uVar9);
            func_0x010eec64(0);
          }
          else {
            Chat_Broadcast__Deserialize(lVar8,uVar9);
          }
          lVar11 = *(long *)(lVar8 + 0x18);
          if ((lVar11 != 0) && (*(int *)(lVar8 + 0x10) == 7)) {
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar8 = LitJson_JsonMapper__ToObject<UserTechLocalInfos>
                              (0,lVar11,_mm_Method_LitJson_JsonMapper_ToObject<ChattingMessage>__);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            Chat_ChattingMessage__DeserializeSubType(lVar8);
            Chat_Chatting__AddChatMsg(param_1,lVar8);
          }
          iVar10 = iVar10 + 1;
        } while (iVar3 != iVar10);
      }
      if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = GuildManager__get_myGuildId(0,0);
      uVar9 = Chat_Chatting__GetGuildRoomId(uVar9,uVar9);
      if ((bRam00000000049fcf1a & 1) == 0) {
        func_0x010c65d4(0x25d5);
        bRam00000000049fcf1a = 1;
      }
      if (*(char *)(param_1 + 0x80) != '\0') {
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar2 = System_String__IsNullOrEmpty(0,uVar9,0);
        if ((uVar2 & 1) == 0) {
          lVar7 = *(long *)(param_1 + 0x48);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          lVar7 = *(long *)(lVar7 + 0x28);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = System_Collections_Generic_List<Ai>__Contains
                            (lVar7,uVar9,
                             _mm_Method_System_Collections_Generic_List<string>_Contains__);
          if ((uVar2 & 1) == 0) {
            lVar7 = *(long *)(param_1 + 0x48);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            lVar7 = *(long *)(lVar7 + 0x28);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_List<tk2dUILayoutItem>__Add
                      (lVar7,uVar9,_mm_Method_System_Collections_Generic_List<string>_Add__);
            lVar7 = *(long *)(param_1 + 0x68);
            if (((*(byte *)(_class_System_StringComparer_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_StringComparer_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar4 = System_StringComparer__get_Ordinal(0,0);
            uVar5 = func_0x010fdda8(_class_System_Collections_Generic_HashSet<string>_TypeInfo);
            System_Collections_Generic_HashSet<string>___ctor
                      (uVar5,uVar4,_mm_Method_System_Collections_Generic_HashSet<string>__ctor__);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_Dictionary<GameObject,_Stack<int>>__Add
                      (lVar7,uVar9,uVar5,
                       _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Add__
                      );
            Chat_Chatting__EmitJoinRoom(param_1,uVar9);
            Chat_Chatting__EmitBroadcast(param_1,0,*(undefined8 *)(param_1 + 0x48),uVar9);
            lVar7 = *(long *)(param_1 + 0x48);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            uVar4 = *(undefined8 *)(lVar7 + 0x18);
            lVar7 = func_0x010fdda8(_class_Chat_ChattingUserRequest_TypeInfo);
            System_Object___ctor(lVar7,0);
            *(undefined8 *)(lVar7 + 0x10) = uVar4;
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
              uVar4 = LitJson_JsonMapper__ToJson(0,lVar7,0);
              lVar7 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
              System_Object___ctor(lVar7,0);
              *(undefined4 *)(lVar7 + 0x10) = 2;
              *(undefined8 *)(lVar7 + 0x18) = uVar4;
              *(undefined8 *)(lVar7 + 0x20) = uVar9;
              *(undefined4 *)(lVar7 + 0x28) = 0xffffffff;
              uVar9 = LitJson_JsonMapper__ToJson(0,lVar7,0);
              if (lVar8 == 0) {
                func_0x010eec64(0);
              }
              uVar4 = _str_broadcastTo;
              if ((bRam0000000004a00249 & 1) == 0) {
                func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar9,0);
                bRam0000000004a00249 = 1;
              }
              if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar9 = System_String__Format(0,_str____0____1__,uVar4,uVar9,0);
              if ((bRam0000000004a0024d & 1) == 0) {
                func_0x010c65d4(0xc5ce);
                bRam0000000004a0024d = 1;
              }
              if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar4 = LitJson_JsonMapper__ToObject(0,uVar9,0);
              lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
              uVar9 = _str__;
              System_Object___ctor(lVar11,0);
              *(undefined4 *)(lVar11 + 0x14) = 2;
              *(undefined4 *)(lVar11 + 0x18) = 0;
              *(undefined8 *)(lVar11 + 0x30) = uVar4;
              *(undefined8 *)(lVar11 + 0x20) = uVar9;
              *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
              *(undefined4 *)(lVar11 + 0x10) = 4;
              lVar7 = lVar8;
              if ((bRam0000000004a0024f & 1) == 0) {
                lVar7 = func_0x010c65d4(0xc5cf);
                bRam0000000004a0024f = 1;
              }
              lVar1 = *(long *)(lVar8 + 0x48);
              if (*(long *)(lVar8 + 0x50) == 0) {
                lVar7 = func_0x010eec64(0);
              }
              uVar9 = SocketIO_Encoder__Encode(lVar7,lVar11);
              if (lVar1 == 0) {
                func_0x010eec64(0);
              }
              WebSocketSharp_WebSocket__Send(lVar1,uVar9,0);
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


