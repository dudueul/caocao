// name: Chat.Chatting$$OnError
// entry: 01d2e6b8

void Chat_Chatting__OnError(long *param_1)

{
  ushort uVar1;
  uint uVar2;
  long lVar3;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar4;
  ulong uVar5;
  long *plVar6;
  undefined8 uVar7;
  undefined8 *puVar8;
  long lVar9;
  int *piVar10;
  
  lVar3 = param_1[0x17];
  if (lVar3 == 0) {
    return;
  }
  if (*(long *)(lVar3 + 0x58) != 0) {
    System_EventHandler__Invoke(*(long *)(lVar3 + 0x58),param_1,0,0);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x10);
  plVar6 = *(long **)(lVar3 + 0x20);
  puVar8 = *(undefined8 **)(lVar3 + 0x28);
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
          (*(long *)(lVar3 + 0x18) != 0)))) {
        uVar4 = func_0x01101d58(puVar8);
        func_0x01101dd4(puVar8);
        uVar5 = func_0x010ba65c();
        if ((uVar4 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar8 + 0x4c);
          if ((uVar5 & 1) == 0) {
            lVar3 = *param_1 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
            puVar8 = *(undefined8 **)(lVar3 + 0x138);
          }
          else {
            lVar3 = func_0x01101dd4(puVar8);
            lVar9 = *param_1;
            if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
              uVar4 = 0;
              piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == lVar3) {
                  puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x02c1b860;
                }
                uVar4 = uVar4 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar4 < *(ushort *)(lVar9 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(param_1,lVar3,(ulong)uVar1);
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
            lVar3 = *param_1;
            if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
              uVar4 = 0;
              piVar10 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == puVar8[3]) {
                  lVar3 = lVar3 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x02c1b808;
                }
                uVar4 = uVar4 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar4 < *(ushort *)(lVar3 + 0x122));
            }
            lVar3 = func_0x010bc584(param_1);
code_r0x02c1b808:
            uVar7 = *(undefined8 *)(lVar3 + 8);
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
       (*(long *)(lVar3 + 0x18) != 0)) {
      uVar4 = func_0x01101d58(puVar8);
      func_0x01101dd4(puVar8);
      uVar5 = func_0x010ba65c();
      if ((uVar4 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar8 + 0x4c);
        if ((uVar5 & 1) == 0) {
          lVar3 = *plVar6 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
          puVar8 = *(undefined8 **)(lVar3 + 0x138);
        }
        else {
          lVar3 = func_0x01101dd4(puVar8);
          lVar9 = *plVar6;
          if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
            uVar4 = 0;
            piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == lVar3) {
                puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x02c1b83c;
              }
              uVar4 = uVar4 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar4 < *(ushort *)(lVar9 + 0x122));
          }
          puVar8 = (undefined8 *)func_0x010bc584(plVar6,lVar3,(ulong)uVar1);
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
          lVar3 = *plVar6;
          if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
            uVar4 = 0;
            piVar10 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == puVar8[3]) {
                lVar3 = lVar3 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x02c1b7d0;
              }
              uVar4 = uVar4 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar4 < *(ushort *)(lVar3 + 0x122));
          }
          lVar3 = func_0x010bc584(plVar6);
code_r0x02c1b7d0:
          uVar7 = *(undefined8 *)(lVar3 + 8);
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


