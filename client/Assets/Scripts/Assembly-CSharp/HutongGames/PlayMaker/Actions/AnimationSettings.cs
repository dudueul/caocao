using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AnimationSettings : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString animName;

		public WrapMode wrapMode;

		public AnimationBlendMode blendMode;

		public FsmFloat speed;

		public FsmInt layer;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAnimationSettings()
		{
		}
	}
}
