using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmVariable : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		[HideTypeFilter]
		public FsmVar storeValue;

		public bool everyFrame;

		private GameObject cachedGO;

		private PlayMakerFSM sourceFsm;

		private INamedVariable sourceVariable;

		private NamedVariable targetVariable;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void InitFsmVar()
		{
		}

		private void DoGetFsmVariable()
		{
		}
	}
}
