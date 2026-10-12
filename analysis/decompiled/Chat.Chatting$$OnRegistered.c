// name: Chat.Chatting$$OnRegistered
// entry: 01d2d36c

void Chat_Chatting__OnRegistered(long *param_1)

{
  ushort uVar1;
  uint uVar2;
  ulong uVar3;
  long lVar4;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 *puVar8;
  long lVar9;
  int *piVar10;
  
  uVar3 = Chat_Chatting__get_IsConnected();
  if ((uVar3 & 1) != 0) {
    Chat_Chatting__GetBlockListFromServer(param_1);
  }
  *(undefined1 *)(param_1 + 0x10) = 1;
  Chat_Chatting__EmitChattingUserMeToJoinedChannelsAndPub(param_1);
  lVar4 = param_1[0x15];
  if (lVar4 == 0) {
    return;
  }
  if (*(long *)(lVar4 + 0x58) != 0) {
    System_EventHandler__Invoke(*(long *)(lVar4 + 0x58),param_1,0,0);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x10);
  plVar6 = *(long **)(lVar4 + 0x20);
  puVar8 = *(undefined8 **)(lVar4 + 0x28);
  func_0x010da364(puVar8);
  uVar2 = func_0x01101d6c(puVar8);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar8 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x02c1b668. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar6,param_1,0,puVar8);
      return;
    }
    plVar6 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar8 + 0x4e) != '\x02') {
      if ((*(short *)((long)puVar8 + 0x4c) != -1) &&
         (((plVar6 == (long *)0x0 || ((*(byte *)(*plVar6 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar4 + 0x18) != 0)))) {
        uVar3 = func_0x01101d58(puVar8);
        func_0x01101dd4(puVar8);
        uVar5 = func_0x010ba65c();
        if ((uVar3 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar8 + 0x4c);
          if ((uVar5 & 1) == 0) {
            lVar4 = *param_1 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
            puVar8 = *(undefined8 **)(lVar4 + 0x138);
          }
          else {
            lVar4 = func_0x01101dd4(puVar8);
            lVar9 = *param_1;
            if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
              uVar3 = 0;
              piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == lVar4) {
                  puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x02c1b860;
                }
                uVar3 = uVar3 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar3 < *(ushort *)(lVar9 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(param_1,lVar4,(ulong)uVar1);
code_r0x02c1b860:
            UNRECOVERED_JUMPTABLE = (code *)*puVar8;
            puVar8 = (undefined8 *)puVar8[1];
          }
        }
        else {
          if ((uVar5 & 1) == 0) {
            uVar7 = *(undefined8 *)
                     (*param_1 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar4 = *param_1;
            if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
              uVar3 = 0;
              piVar10 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == puVar8[3]) {
                  lVar4 = lVar4 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x02c1b808;
                }
                uVar3 = uVar3 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar3 < *(ushort *)(lVar4 + 0x122));
            }
            lVar4 = func_0x010bc584(param_1);
code_r0x02c1b808:
            uVar7 = *(undefined8 *)(lVar4 + 8);
          }
          puVar8 = (undefined8 *)func_0x010da294(uVar7,puVar8);
          UNRECOVERED_JUMPTABLE = (code *)*puVar8;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x02c1b6bc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(param_1,0,puVar8);
      return;
    }
    if (((*(short *)((long)puVar8 + 0x4c) != -1) &&
        ((plVar6 == (long *)0x0 || ((*(byte *)(*plVar6 + 0x10d) & 1) == 0)))) &&
       (*(long *)(lVar4 + 0x18) != 0)) {
      uVar3 = func_0x01101d58(puVar8);
      func_0x01101dd4(puVar8);
      uVar5 = func_0x010ba65c();
      if ((uVar3 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar8 + 0x4c);
        if ((uVar5 & 1) == 0) {
          lVar4 = *plVar6 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
          puVar8 = *(undefined8 **)(lVar4 + 0x138);
        }
        else {
          lVar4 = func_0x01101dd4(puVar8);
          lVar9 = *plVar6;
          if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
            uVar3 = 0;
            piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == lVar4) {
                puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x02c1b83c;
              }
              uVar3 = uVar3 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar3 < *(ushort *)(lVar9 + 0x122));
          }
          puVar8 = (undefined8 *)func_0x010bc584(plVar6,lVar4,(ulong)uVar1);
code_r0x02c1b83c:
          UNRECOVERED_JUMPTABLE = (code *)*puVar8;
          puVar8 = (undefined8 *)puVar8[1];
        }
      }
      else {
        if ((uVar5 & 1) == 0) {
          uVar7 = *(undefined8 *)(*plVar6 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar4 = *plVar6;
          if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
            uVar3 = 0;
            piVar10 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == puVar8[3]) {
                lVar4 = lVar4 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x02c1b7d0;
              }
              uVar3 = uVar3 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar3 < *(ushort *)(lVar4 + 0x122));
          }
          lVar4 = func_0x010bc584(plVar6);
code_r0x02c1b7d0:
          uVar7 = *(undefined8 *)(lVar4 + 8);
        }
        puVar8 = (undefined8 *)func_0x010da294(uVar7,puVar8);
        UNRECOVERED_JUMPTABLE = (code *)*puVar8;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x02c1b694. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar6,param_1,0,puVar8);
  return;
}


