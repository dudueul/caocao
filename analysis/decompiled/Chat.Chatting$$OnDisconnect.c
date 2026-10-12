// name: Chat.Chatting$$OnDisconnect
// entry: 01d2d1e0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__OnDisconnect(long *param_1)

{
  ushort uVar1;
  uint uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar5;
  ulong uVar6;
  long *plVar7;
  undefined8 *puVar8;
  long lVar9;
  int *piVar10;
  long lVar11;
  
  if ((bRam00000000049fcf2a & 1) == 0) {
    func_0x010c65d4(0x25e7);
    bRam00000000049fcf2a = 1;
  }
  uVar3 = ___L(0,_DAT_049dd940,0);
  uVar4 = func_0x01101ad4(_class_object___TypeInfo,0);
  PanelPopupCenterText__Show(0x40000000,0,uVar3,0,uVar4,0);
  lVar11 = param_1[9];
  *(undefined1 *)(param_1 + 0x10) = 0;
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  *(undefined4 *)(lVar11 + 0x20) = 0;
  lVar11 = param_1[9];
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  lVar11 = *(long *)(lVar11 + 0x28);
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUICamera>__Clear
            (lVar11,_mm_Method_System_Collections_Generic_List<string>_Clear__);
  lVar11 = param_1[0xb];
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_HashSet<IClippable>__Clear
            (lVar11,_mm_Method_System_Collections_Generic_HashSet<string>_Clear__);
  lVar11 = param_1[0xf];
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<tk2dUICamera>__Clear
            (lVar11,_mm_Method_System_Collections_Generic_List<Notification>_Clear__);
  lVar11 = param_1[0xc];
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<HttpListenerContext,_HttpListenerContext>__Clear
            (lVar11,_mm_Method_System_Collections_Generic_Dictionary<string,_ChattingUser>_Clear__);
  lVar11 = param_1[0xd];
  if (lVar11 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<HttpListenerContext,_HttpListenerContext>__Clear
            (lVar11,
             _mm_Method_System_Collections_Generic_Dictionary<string,_HashSet<string>>_Clear__);
  lVar11 = param_1[0x14];
  if (lVar11 == 0) {
    return;
  }
  if (*(long *)(lVar11 + 0x58) != 0) {
    System_EventHandler__Invoke(*(long *)(lVar11 + 0x58),param_1,0,0);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar11 + 0x10);
  plVar7 = *(long **)(lVar11 + 0x20);
  puVar8 = *(undefined8 **)(lVar11 + 0x28);
  func_0x010da364(puVar8);
  uVar2 = func_0x01101d6c(puVar8);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar8 + 0x4e) != '\x02') {
                    /* WARNING: Could not recover jumptable at 0x02c1b668. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar7,param_1,0,puVar8);
      return;
    }
    plVar7 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar8 + 0x4e) != '\x02') {
      if ((*(short *)((long)puVar8 + 0x4c) != -1) &&
         (((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar11 + 0x18) != 0)))) {
        uVar5 = func_0x01101d58(puVar8);
        func_0x01101dd4(puVar8);
        uVar6 = func_0x010ba65c();
        if ((uVar5 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar8 + 0x4c);
          if ((uVar6 & 1) == 0) {
            lVar11 = *param_1 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar11 + 0x130);
            puVar8 = *(undefined8 **)(lVar11 + 0x138);
          }
          else {
            lVar11 = func_0x01101dd4(puVar8);
            lVar9 = *param_1;
            if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
              uVar5 = 0;
              piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == lVar11) {
                  puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x02c1b860;
                }
                uVar5 = uVar5 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar5 < *(ushort *)(lVar9 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(param_1,lVar11,(ulong)uVar1);
code_r0x02c1b860:
            UNRECOVERED_JUMPTABLE = (code *)*puVar8;
            puVar8 = (undefined8 *)puVar8[1];
          }
        }
        else {
          if ((uVar6 & 1) == 0) {
            uVar3 = *(undefined8 *)
                     (*param_1 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar11 = *param_1;
            if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
              uVar5 = 0;
              piVar10 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
              do {
                if (*(long *)(piVar10 + -2) == puVar8[3]) {
                  lVar11 = lVar11 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) *
                                    0x10 + 0x130;
                  goto code_r0x02c1b808;
                }
                uVar5 = uVar5 + 1;
                piVar10 = piVar10 + 4;
              } while (uVar5 < *(ushort *)(lVar11 + 0x122));
            }
            lVar11 = func_0x010bc584(param_1);
code_r0x02c1b808:
            uVar3 = *(undefined8 *)(lVar11 + 8);
          }
          puVar8 = (undefined8 *)func_0x010da294(uVar3,puVar8);
          UNRECOVERED_JUMPTABLE = (code *)*puVar8;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x02c1b6bc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(param_1,0,puVar8);
      return;
    }
    if (((*(short *)((long)puVar8 + 0x4c) != -1) &&
        ((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)))) &&
       (*(long *)(lVar11 + 0x18) != 0)) {
      uVar5 = func_0x01101d58(puVar8);
      func_0x01101dd4(puVar8);
      uVar6 = func_0x010ba65c();
      if ((uVar5 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar8 + 0x4c);
        if ((uVar6 & 1) == 0) {
          lVar11 = *plVar7 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar11 + 0x130);
          puVar8 = *(undefined8 **)(lVar11 + 0x138);
        }
        else {
          lVar11 = func_0x01101dd4(puVar8);
          lVar9 = *plVar7;
          if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
            uVar5 = 0;
            piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == lVar11) {
                puVar8 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x02c1b83c;
              }
              uVar5 = uVar5 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar5 < *(ushort *)(lVar9 + 0x122));
          }
          puVar8 = (undefined8 *)func_0x010bc584(plVar7,lVar11,(ulong)uVar1);
code_r0x02c1b83c:
          UNRECOVERED_JUMPTABLE = (code *)*puVar8;
          puVar8 = (undefined8 *)puVar8[1];
        }
      }
      else {
        if ((uVar6 & 1) == 0) {
          uVar3 = *(undefined8 *)(*plVar7 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar11 = *plVar7;
          if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
            uVar5 = 0;
            piVar10 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
            do {
              if (*(long *)(piVar10 + -2) == puVar8[3]) {
                lVar11 = lVar11 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar8 + 0x4c)) * 0x10
                         + 0x130;
                goto code_r0x02c1b7d0;
              }
              uVar5 = uVar5 + 1;
              piVar10 = piVar10 + 4;
            } while (uVar5 < *(ushort *)(lVar11 + 0x122));
          }
          lVar11 = func_0x010bc584(plVar7);
code_r0x02c1b7d0:
          uVar3 = *(undefined8 *)(lVar11 + 8);
        }
        puVar8 = (undefined8 *)func_0x010da294(uVar3,puVar8);
        UNRECOVERED_JUMPTABLE = (code *)*puVar8;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x02c1b694. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar7,param_1,0,puVar8);
  return;
}


