// name: Chat.Chatting$$GetDestRoomId
// entry: 01d2f4d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long Chat_Chatting__GetDestRoomId(long param_1,long *param_2)

{
  int iVar1;
  byte bVar2;
  long lVar3;
  uint uVar4;
  int iVar5;
  long lVar6;
  undefined8 uVar7;
  long lVar8;
  
  if ((bRam00000000049fcf3b & 1) == 0) {
    func_0x010c65d4(0x25ca);
    bRam00000000049fcf3b = 1;
  }
  if (param_2 != (long *)0x0) {
    lVar8 = *param_2;
    bVar2 = *(byte *)(lVar8 + 0x124);
    if ((bVar2 < *(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124)) ||
       (*(long *)(*(long *)(lVar8 + 200) +
                  (ulong)*(byte *)(_class_Chat_ChattingMessage_TypeInfo + 0x124) * 8 + -8) !=
        _class_Chat_ChattingMessage_TypeInfo)) {
      uVar4 = 6;
      if ((*(byte *)(_class_Chat_WhisperResponse_TypeInfo + 0x124) <= bVar2) &&
         (uVar4 = 6,
         *(long *)(*(long *)(lVar8 + 200) +
                   (ulong)*(byte *)(_class_Chat_WhisperResponse_TypeInfo + 0x124) * 8 + -8) ==
         _class_Chat_WhisperResponse_TypeInfo)) {
        uVar4 = 1;
      }
      if ((*(byte *)(_class_Chat_Notification_TypeInfo + 0x124) <= bVar2) &&
         (*(long *)(*(long *)(lVar8 + 200) +
                    (ulong)*(byte *)(_class_Chat_Notification_TypeInfo + 0x124) * 8 + -8) ==
          _class_Chat_Notification_TypeInfo)) {
        uVar4 = 2;
      }
      goto Chat_Chatting__GetDestRoomId;
    }
    lVar8 = param_2[3];
    if (((*(byte *)(_class_System_Convert_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Convert_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar7 = System_Convert__ToUInt64(0,lVar8,0);
    uVar4 = Chat_Chatting__IsBlockUser(param_1,uVar7);
    if ((uVar4 & 1) == 0) {
      uVar4 = *(uint *)((long)param_2 + 0x34);
      goto Chat_Chatting__GetDestRoomId;
    }
  }
  uVar4 = 6;
Chat_Chatting__GetDestRoomId:
  if ((bRam00000000049fcf3a & 1) == 0) {
    func_0x010c65d4(0x25c9);
    bRam00000000049fcf3a = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar8 = **(long **)(_class_string_TypeInfo + 0xb8);
  if (uVar4 < 6) {
    iVar5 = *(int *)(&UNK_03997e50 + (long)(int)uVar4 * 4);
    if (iVar5 == 7) {
      if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      GuildManager__get_myGuildId(0,0);
      if ((bRam00000000049fcf37 & 1) == 0) {
        func_0x010c65d4(0x25cd);
        bRam00000000049fcf37 = 1;
      }
      uVar7 = func_0x010fdd08(_class_ulong_TypeInfo,&stack0xffffffffffffffd8);
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_string_TypeInfo);
      }
      lVar8 = System_String__Format(0,_str_guildRoom__0_,uVar7,0);
      return lVar8;
    }
    if (iVar5 == 6) {
      lVar8 = *(long *)(param_1 + 0x48);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar8 + 0x18);
      if ((bRam00000000049fcf35 & 1) == 0) {
        func_0x010c65d4(0x25d2);
        bRam00000000049fcf35 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar3 = _str__sub_;
      if ((bRam0000000004a03ad9 & 1) == 0) {
        func_0x010c65d4(0xcce8,lVar8,_str__sub_,0);
        bRam0000000004a03ad9 = 1;
      }
      if ((lVar8 == 0) || (iVar5 = *(int *)(lVar8 + 0x10), iVar5 == 0)) {
        if ((lVar3 == 0) || (lVar8 = lVar3, *(int *)(lVar3 + 0x10) == 0)) {
          if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar8 = **(long **)(_class_string_TypeInfo + 0xb8);
        }
      }
      else if ((lVar3 != 0) && (iVar1 = *(int *)(lVar3 + 0x10), iVar1 != 0)) {
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar6 = func_0x01103084(iVar1 + iVar5);
        iVar5 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
        uVar7 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
        System_String__CharCopy
                  (uVar7,lVar6 + iVar5,lVar8 + (int)uVar7,*(undefined4 *)(lVar8 + 0x10));
        iVar5 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
        uVar7 = System_Runtime_CompilerServices_RuntimeHelpers__get_OffsetToStringData(0,0);
        System_String__CharCopy
                  (uVar7,lVar6 + iVar5 + (long)(*(int *)(lVar8 + 0x10) << 1),lVar3 + (int)uVar7,
                   *(undefined4 *)(lVar3 + 0x10));
        lVar8 = lVar6;
      }
      return lVar8;
    }
    if (iVar5 == 5) {
      lVar8 = *(long *)(param_1 + 0x48);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      lVar8 = *(long *)(lVar8 + 0x30);
    }
  }
  return lVar8;
}


