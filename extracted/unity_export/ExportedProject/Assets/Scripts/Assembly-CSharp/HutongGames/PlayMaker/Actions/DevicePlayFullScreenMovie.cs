using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class DevicePlayFullScreenMovie : FsmStateAction
	{
		[RequiredField]
		public FsmString moviePath;

		[RequiredField]
		public FsmColor fadeColor;

		public FullScreenMovieControlMode movieControlMode;

		public FullScreenMovieScalingMode movieScalingMode;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
