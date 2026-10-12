using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetAnimatorLayerWeight : FsmStateActionAnimatorBase
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt layerIndex;

		[RequiredField]
		public FsmFloat layerWeight;

		private Animator _animator;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void GetLayerWeight()
		{
		}
	}
}
