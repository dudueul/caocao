// name: Chat.Chatting$$OnChattingMessage
// entry: 01d2dcc4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnChattingMessage(long param_1,long *param_2)

{
  long lVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  long *plVar7;
  ulong uVar8;
  long lVar9;
  ulong uVar10;
  long lVar11;
  undefined8 uVar12;
  undefined8 uVar13;
  undefined1 auVar14 [16];
  
  if ((bRam00000000049fcf30 & 1) == 0) {
    func_0x010c65d4(0x25e1);
    bRam00000000049fcf30 = 1;
  }
  if (param_2 != (long *)0x0) {
    lVar9 = param_2[3];
    if (((*(byte *)(_class_System_Convert_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_System_Convert_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = System_Convert__ToUInt64(0,lVar9,0);
    uVar2 = Chat_Chatting__IsBlockUser(param_1,uVar5);
    if ((uVar2 & 1) == 0) {
      if (*(int *)((long)param_2 + 0x34) == 1) {
        uVar5 = (**(code **)(*param_2 + 0x170))(param_2,*(undefined8 *)(*param_2 + 0x178));
        lVar11 = *(long *)(param_1 + 0x48);
        lVar9 = lVar11;
        if (lVar11 == 0) {
          func_0x010eec64(0);
          lVar9 = *(long *)(param_1 + 0x48);
          if (lVar9 == 0) {
            plVar7 = (long *)func_0x010eec64(0);
            if ((bRam00000000049fcf6f & 1) == 0) {
              func_0x010c65d4(0x25a1);
              bRam00000000049fcf6f = 1;
            }
            uVar5 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
            if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar9 = LitJson_JsonMapper__ToObject(0,uVar5,0);
            if (lVar9 != 0) {
              if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c();
              }
              uVar3 = Util__GetData(0,lVar9,_str_type,4,0);
              *(undefined4 *)((long)plVar7 + 0x3c) = uVar3;
            }
            return;
          }
        }
        uVar13 = *(undefined8 *)(lVar9 + 0x18);
        uVar12 = *(undefined8 *)(lVar11 + 0x10);
        lVar9 = param_2[6];
        lVar11 = param_2[7];
        uVar6 = func_0x010fdda8(_class_Chat_WhisperResponse_TypeInfo);
        uVar5 = Chat_WhisperResponse___ctor(uVar6,uVar5,uVar12,uVar13,(int)lVar9,0,(int)lVar11);
        uVar5 = Chat_Chatting__GetSubRoomId(uVar5,param_2[4]);
        Chat_Chatting__EmitBroadcast(param_1,4,uVar6,uVar5);
      }
      else {
        iVar4 = *(int *)(param_1 + 0x84);
        *(int *)(param_1 + 0x84) = iVar4 + 1;
        *(int *)(param_2 + 6) = iVar4;
      }
      if (*(long *)(param_1 + 0xc0) != 0) {
        lVar9 = _mm_Method_System_EventHandler<ChattingMessage>_Invoke__;
        System_EventHandler<object>__Invoke(*(long *)(param_1 + 0xc0),param_1,param_2);
        if ((bRam00000000049fcf42 & 1) == 0) {
          func_0x010c65d4(0x25ab);
          bRam00000000049fcf42 = 1;
        }
        uVar5 = Chat_Chatting__GetDestRoomId(param_1,param_2);
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_string_TypeInfo);
        }
        uVar2 = System_String__IsNullOrEmpty(0,uVar5,0);
        if ((uVar2 & 1) == 0) {
          Chat_Chatting__AddChat(param_1,_str_all,param_2);
          Chat_Chatting__TrimChat(param_1,_str_all);
          Chat_Chatting__AddChat(param_1,uVar5,param_2);
          if ((bRam00000000049fcf44 & 1) == 0) {
            func_0x010c65d4(0x25f7);
            bRam00000000049fcf44 = 1;
          }
          lVar11 = *(long *)(param_1 + 0x70);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          uVar8 = System_Collections_Generic_Dictionary<ListenerPrefix,_HttpListener>__ContainsKey
                            (lVar11,uVar5,
                             _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_ContainsKey__
                            );
          if ((uVar8 & 1) != 0) {
            lVar11 = *(long *)(param_1 + 0x70);
            if (lVar11 == 0) {
              func_0x010eec64(0);
            }
            lVar11 = System_Collections_Generic_Dictionary<object,_object>__get_Item
                               (lVar11,uVar5,
                                _mm_Method_System_Collections_Generic_Dictionary<string,_List<EventArgs>>_get_Item__
                               );
            if ((lVar11 != 0) &&
               (iVar4 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                                  (lVar11,
                                   _mm_Method_System_Collections_Generic_List<EventArgs>_get_Count__
                                  ),
               lVar1 = _mm_Method_System_Collections_Generic_List<EventArgs>_RemoveAt__, 199 < iVar4
               )) {
              if ((bRam0000000004a09250 & 1) == 0) {
                func_0x010c65d4(0x7e85);
                bRam0000000004a09250 = 1;
              }
              if (*(int *)(lVar11 + 0x18) == 0) {
                uVar5 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
                System_ArgumentOutOfRangeException___ctor(uVar5,_str_index,0);
                uVar8 = _mm_Method_System_Collections_Generic_List<object>_RemoveAt__;
                func_0x010eebec(uVar5,0);
                auVar14 = func_0x0112148c();
                lVar11 = auVar14._0_8_;
                uVar10 = auVar14._8_8_ & 0xffffffff;
                if (lVar11 == 0) {
                  func_0x010eec64(0);
                }
                (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar9 + 0x18) + 0xc0) + 0x118))
                          (lVar11,uVar10,uVar8 & 0xffffffff);
                if (0 < (int)uVar8) {
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar9 + 0x18) + 0xc0) + 0x120))
                            (lVar11,uVar10,-(int)uVar8);
                  System_Array__Clear(0,*(undefined8 *)(lVar11 + 0x10),
                                      *(undefined4 *)(lVar11 + 0x18),uVar8 & 0xffffffff,0);
                  *(int *)(lVar11 + 0x1c) = *(int *)(lVar11 + 0x1c) + 1;
                }
                return;
              }
              (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar1 + 0x18) + 0xc0) + 0x120))
                        (lVar11,0,0xffffffff);
              System_Array__Clear(0,*(undefined8 *)(lVar11 + 0x10),*(undefined4 *)(lVar11 + 0x18),1,
                                  0);
              *(int *)(lVar11 + 0x1c) = *(int *)(lVar11 + 0x1c) + 1;
              return;
            }
          }
          return;
        }
        return;
      }
    }
  }
  return;
}


