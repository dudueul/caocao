// name: Chat.Chatting$$ChatToChannel
// entry: 01d2c240

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__ChatToChannel
               (long param_1,undefined8 param_2,undefined8 param_3,undefined4 param_4,
               undefined4 param_5)

{
  long lVar1;
  int iVar2;
  undefined4 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  undefined8 uVar8;
  long lVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  double extraout_d0;
  double dVar12;
  undefined4 uStack_80;
  undefined4 uStack_7c;
  undefined8 uStack_78;
  
  if ((bRam00000000049fcf1d & 1) == 0) {
    func_0x010c65d4(0x25b2);
    bRam00000000049fcf1d = 1;
  }
  if (*(char *)(param_1 + 0x80) != '\0') {
    uVar11 = ServerTime__get_GetNowUnixTime(0,0);
    Chat_Chatting__CleanCoolTime(param_1);
    iVar2 = Chat_Chatting__GetAvaliableChatMsgCount(param_1,param_4);
    uVar3 = Chat_Chatting__GetFirstCoolTime(uVar11,param_1,param_4);
    if (0 < iVar2) {
      Chat_Chatting__AddCoolTime(uVar11,param_1,param_4);
      lVar9 = *(long *)(param_1 + 0x48);
      lVar7 = lVar9;
      if (lVar9 == 0) {
        func_0x010eec64(0);
        lVar7 = *(long *)(param_1 + 0x48);
        if (lVar7 == 0) {
code_r0x01d2c544:
          lVar7 = func_0x010eec64(0);
          if ((bRam00000000049fcf4b & 1) == 0) {
            func_0x010c65d4(0x25b6);
            bRam00000000049fcf4b = 1;
          }
          lVar9 = *(long *)(lVar7 + 0x88);
          if (lVar9 == 0) {
            lVar9 = func_0x010fdda8(_class_System_Collections_Generic_List<double>_TypeInfo);
            System_Collections_Generic_List<double>___ctor
                      (lVar9,_mm_Method_System_Collections_Generic_List<double>__ctor__);
            *(long *)(lVar7 + 0x88) = lVar9;
            if (lVar9 == 0) {
              func_0x010eec64(0);
              lVar9 = 0;
            }
          }
          iVar2 = System_Collections_Generic_List<double>__get_Count
                            (lVar9,_mm_Method_System_Collections_Generic_List<double>_get_Count__);
          while (iVar2 = iVar2 + -1, -1 < iVar2) {
            lVar9 = *(long *)(lVar7 + 0x88);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            dVar12 = (double)System_Collections_Generic_List<double>__get_Item
                                       (lVar9,iVar2,
                                        _mm_Method_System_Collections_Generic_List<double>_get_Item__
                                       );
            if (dVar12 < extraout_d0) {
              lVar9 = *(long *)(lVar7 + 0x88);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              System_Collections_Generic_List<double>__RemoveAt
                        (lVar9,iVar2,_mm_Method_System_Collections_Generic_List<double>_RemoveAt__);
            }
          }
          return;
        }
      }
      iVar2 = *(int *)(param_1 + 0x84);
      uVar10 = *(undefined8 *)(lVar7 + 0x18);
      uVar11 = *(undefined8 *)(lVar9 + 0x10);
      *(int *)(param_1 + 0x84) = iVar2 + 1;
      lVar7 = func_0x010fdda8(_class_Chat_ChattingMessage_TypeInfo);
      Chat_ChattingMessage___ctor(lVar7,param_3,uVar11,uVar10,param_2,iVar2,param_4,param_5);
      if (*(char *)(param_1 + 0x80) == '\0') {
        return;
      }
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar9 = (long)(*(int *)(lVar7 + 0x34) + 2);
      if (2 < *(int *)(lVar7 + 0x34) - 1U) {
        lVar9 = 0;
      }
      uVar3 = *(undefined4 *)(&UNK_03997e00 + lVar9 * 4);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar11 = *(undefined8 *)(lVar7 + 0x28);
      if ((bRam00000000049fcf28 & 1) == 0) {
        func_0x010c65d4(0x25bc);
        bRam00000000049fcf28 = 1;
      }
      if (*(char *)(param_1 + 0x80) == '\0') {
        return;
      }
      lVar9 = *(long *)(param_1 + 0x20);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = LitJson_JsonMapper__ToJson(0,lVar7,0);
      lVar7 = func_0x010fdda8(_class_Chat_Broadcast_TypeInfo);
      System_Object___ctor(lVar7,0);
      *(undefined4 *)(lVar7 + 0x10) = uVar3;
      *(undefined8 *)(lVar7 + 0x18) = uVar10;
      *(undefined8 *)(lVar7 + 0x20) = uVar11;
      *(undefined4 *)(lVar7 + 0x28) = 0xffffffff;
      uVar11 = LitJson_JsonMapper__ToJson(0,lVar7,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = _str_broadcastTo;
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,_str_broadcastTo,uVar11,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = System_String__Format(0,_str____0____1__,uVar10,uVar11,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = LitJson_JsonMapper__ToObject(0,uVar11,0);
      lVar6 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar11 = _str__;
      System_Object___ctor(lVar6,0);
      *(undefined4 *)(lVar6 + 0x14) = 2;
      *(undefined4 *)(lVar6 + 0x18) = 0;
      *(undefined8 *)(lVar6 + 0x30) = uVar10;
      *(undefined8 *)(lVar6 + 0x20) = uVar11;
      *(undefined4 *)(lVar6 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar6 + 0x10) = 4;
      lVar7 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar7 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar7 = func_0x010eec64(0);
      }
      uVar11 = SocketIO_Encoder__Encode(lVar7,lVar6);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar11,0);
      return;
    }
    uVar11 = ___L(0,
                  _str__0_초내에__1_개의_글까지만_입력_할_수_있습니다___2_초_남았습니다_
                  ,0);
    if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Localization_TypeInfo);
    }
    uVar11 = Localization__Get(0,uVar11,0);
    uStack_78 = 0x403e000000000000;
    uVar10 = func_0x010fdd08(_class_double_TypeInfo,&uStack_78);
    uStack_7c = 5;
    uVar4 = func_0x010fdd08(_class_int_TypeInfo,&uStack_7c);
    uStack_80 = uVar3;
    uVar5 = func_0x010fdd08(_class_int_TypeInfo,&uStack_80);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar11 = System_String__Format(0,uVar11,uVar10,uVar4,uVar5,0);
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      uVar4 = func_0x010eec64(0);
      uVar10 = uRam0000000000000010;
      lVar7 = *(long *)(param_1 + 0x48);
      lVar9 = lVar7;
      if (lVar7 == 0) {
        uVar4 = func_0x010eec64(0);
        lVar9 = *(long *)(param_1 + 0x48);
        if (*(long *)(param_1 + 0x48) == 0) goto code_r0x01d2c544;
      }
    }
    else {
      uVar10 = *(undefined8 *)(lVar7 + 0x10);
      uVar4 = uVar11;
      lVar9 = lVar7;
    }
    uVar8 = *(undefined8 *)(lVar7 + 0x18);
    uVar4 = Chat_Chatting__GetSubRoomId(uVar4,*(undefined8 *)(lVar9 + 0x18));
    iVar2 = *(int *)(param_1 + 0x84);
    *(int *)(param_1 + 0x84) = iVar2 + 1;
    uVar5 = func_0x010fdda8(_class_Chat_ChattingMessage_TypeInfo);
    Chat_ChattingMessage___ctor(uVar5,uVar11,uVar10,uVar8,uVar4,iVar2,4,0);
    Chat_Chatting__EmitChattingMessage(param_1,uVar5);
  }
  return;
}


