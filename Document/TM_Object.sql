USE [TM_Object]
GO

/****** Object:  Table [dbo].[BaseOPC]    Script Date: 08/07/2016 10:00:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[BaseOPC](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[DateImport] [datetime] NULL,
	[EventType] [int] NULL,
	[ItemName] [varchar](255) NULL,
	[PropValue] [varchar](255) NULL,
	[ObjID] [varchar](255) NULL,
	[ObjType] [int] NULL,
	[PropType] [int] NULL,
	[TimeStamp] [datetime] NULL,
	[opc] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[BaseOPC]  WITH CHECK ADD  CONSTRAINT [FK4F6505F193126EDC] FOREIGN KEY([opc])
REFERENCES [dbo].[OPCServer] ([id])
GO

ALTER TABLE [dbo].[BaseOPC] CHECK CONSTRAINT [FK4F6505F193126EDC]
GO

