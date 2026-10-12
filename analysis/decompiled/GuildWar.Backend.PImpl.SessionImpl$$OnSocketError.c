// name: GuildWar.Backend.PImpl.SessionImpl$$OnSocketError
// entry: 01b32740

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__OnSocketError(long param_1,long *param_2)

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
  
  if ((bRam00000000049fbe09 & 1) == 0) {
    func_0x010c65d4(0xc02a);
    bRam00000000049fbe09 = 1;
  }
  *(undefined4 *)(param_1 + 0x98) = 0;
  *(undefined1 *)(param_1 + 0x9c) = 0;
  *(undefined1 *)(param_1 + 0x9d) = 0;
  *(undefined1 *)(param_1 + 0xa6) = 0;
  *(undefined4 *)(param_1 + 0xa0) = 0x40a00000;
  *(undefined2 *)(param_1 + 0xa4) = 0;
  GuildWar_Backend_PImpl_SessionImpl__ResetSocketWorldRooms(param_1);
  lVar3 = *(long *)(param_1 + 0x58);
  if (lVar3 == 0) {
    return;
  }
  if (*(long *)(lVar3 + 0x58) != 0) {
    System_Action<tk2dUIUpDownHoverButton>__Invoke
              (*(long *)(lVar3 + 0x58),param_2,_mm_Method_System_Action<SocketIOEvent>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x10);
  plVar6 = *(long **)(lVar3 + 0x20);
  puVar8 = *(undefined8 **)(lVar3 + 0x28);
  func_0x010da364(puVar8);
  uVar2 = func_0x01101d6c(puVar8);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar8 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a3560. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar6,param_2,puVar8);
      return;
    }
    plVar6 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar8 + 0x4e) != '\x01') {
      if ((*(short *)((long)puVar8 + 0x4c) != -1) &&
         (((plVar6 == (long *)0x0 || ((*(byte *)(*plVar6 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar3 + 0x18) != 0)))) {
        uVar4 = func_0x01101d58(puVar8);
        func_0x01101dd4(puVar8);
        uVar5 = func_0x010ba65c();
        if ((uVar4 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar8 + 0x4c);
          if ((uVar5 & 1) == 0) {
            lVar3 = *param_2 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
            puVar8 = *(undefined8 **)(lVar3 + 0x138);
          }
          else {
            lVar3 = func_0x01101dd4(puVar8);
            lVar9 = *param_2;
            if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
              uVar4 = 0;
              piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == lVar3) {
                  puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029a3740;
                }
                uVar4 = uVar4 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar4 < *(ushort *)(lVar9 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(param_2,lVar3,(ulong)uVar1);
code_r0x029a3740:
            UNRECOVERED_JUMPTABLE = (code *)*puVar8;
            puVar8 = (undefined8 *)puVar8[1];
          }
        }
        else {
          if ((uVar5 & 1) == 0) {
            uVar7 = *(undefined8 *)
                     (*param_2 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar3 = *param_2;
            if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
              uVar4 = 0;
              piVar10 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == puVar8[3]) {
                  lVar3 = lVar3 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x029a36f0;
                }
                uVar4 = uVar4 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar4 < *(ushort *)(lVar3 + 0x122));
            }
            lVar3 = func_0x010bc584(param_2);
code_r0x029a36f0:
            uVar7 = *(undefined8 *)(lVar3 + 8);
          }
          puVar8 = (undefined8 *)func_0x010da294(uVar7,puVar8);
          UNRECOVERED_JUMPTABLE = (code *)*puVar8;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029a35a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(param_2,puVar8);
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
                goto code_r0x029a3720;
              }
              uVar4 = uVar4 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar4 < *(ushort *)(lVar9 + 0x122));
          }
          puVar8 = (undefined8 *)func_0x010bc584(plVar6,lVar3,(ulong)uVar1);
code_r0x029a3720:
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
                goto code_r0x029a36bc;
              }
              uVar4 = uVar4 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar4 < *(ushort *)(lVar3 + 0x122));
          }
          lVar3 = func_0x010bc584(plVar6);
code_r0x029a36bc:
          uVar7 = *(undefined8 *)(lVar3 + 8);
        }
        puVar8 = (undefined8 *)func_0x010da294(uVar7,puVar8);
        UNRECOVERED_JUMPTABLE = (code *)*puVar8;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029a3584. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar6,param_2,puVar8);
  return;
}


