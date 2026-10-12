using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmVariables : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		[HideTypeFilter]
		public FsmVar[] getVariables;

		public bool everyFrame;

		private GameObject cachedGO;

		private PlayMakerFSM sourceFsm;

		private INamedVariable[] sourceVariables;

		private NamedVariable[] targetVariables;

		public override void Reset()
		{
		}

		private void InitFsmVars()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetFsmVariables()
		{
		}
	}
}
