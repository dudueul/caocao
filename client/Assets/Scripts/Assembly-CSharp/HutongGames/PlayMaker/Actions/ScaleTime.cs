namespace HutongGames.PlayMaker.Actions
{
	public class ScaleTime : FsmStateAction
	{
		[RequiredField]
		public FsmFloat timeScale;

		public FsmBool adjustFixedDeltaTime;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoTimeScale()
		{
		}
	}
}
