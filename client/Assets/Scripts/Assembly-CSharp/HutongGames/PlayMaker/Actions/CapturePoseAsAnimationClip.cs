using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class CapturePoseAsAnimationClip : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool position;

		public FsmBool rotation;

		public FsmBool scale;

		[RequiredField]
		public FsmObject storeAnimationClip;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoCaptureAnimationClip()
		{
		}

		private void CaptureTransform(Transform transform, string path, AnimationClip clip)
		{
		}

		private void CapturePosition(Transform transform, string path, AnimationClip clip)
		{
		}

		private void CaptureRotation(Transform transform, string path, AnimationClip clip)
		{
		}

		private void CaptureScale(Transform transform, string path, AnimationClip clip)
		{
		}

		private void SetConstantCurve(AnimationClip clip, string childPath, string propertyPath, float value)
		{
		}
	}
}
