namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Add : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		[RequiredField]
		public FsmVector2 addVector;

		public bool everyFrame;

		public bool perSecond;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoVector2Add()
		{
		}
	}
}
