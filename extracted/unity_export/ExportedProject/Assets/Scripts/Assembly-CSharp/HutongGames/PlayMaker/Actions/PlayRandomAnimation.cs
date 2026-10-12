using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class PlayRandomAnimation : BaseAnimationAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString[] animations;

		public FsmFloat[] weights;

		public PlayMode playMode;

		public FsmFloat blendTime;

		public FsmEvent finishEvent;

		public FsmEvent loopEvent;

		public bool stopOnExit;

		private AnimationState anim;

		private float prevAnimtTime;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoPlayRandomAnimation()
		{
		}

		private void DoPlayAnimation(string animName)
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		private void StopAnimation()
		{
		}
	}
}
