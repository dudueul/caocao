namespace HutongGames.PlayMaker.Actions
{
	public class Vector2ClampMagnitude : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		[RequiredField]
		public FsmFloat maxLength;

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

		private void DoVector2ClampMagnitude()
		{
		}
	}
}
