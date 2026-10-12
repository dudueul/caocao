using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetAnimatorLayerWeight : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		[RequiredField]
		public FsmFloat layerWeight;

		public bool everyFrame;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoLayerWeight()
		{
		}
	}
}
